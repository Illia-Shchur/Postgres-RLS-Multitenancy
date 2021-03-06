create table public.users
(
    id       integer generated by default as identity
        constraint users_pkey
            primary key,
    password varchar not null,
    username varchar not null
);

alter table public.users
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on public.users to app_user;

create table public.tenant
(
    id     integer generated always as identity
        constraint tenant_pkey
            primary key,
    tenant varchar not null
);

alter table public.tenant
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on public.tenant to app_user;

create table public.user_tenant
(
    user_id   integer not null
        constraint "USER_FK"
            references public.users,
    tenant_id integer not null
        constraint "TENANT_FK"
            references public.tenant,
    constraint user_tenant_pkey
        primary key (user_id, tenant_id)
);

alter table public.user_tenant
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on public.user_tenant to app_user;

create table public.user_info
(
    id        integer generated always as identity
        constraint user_info_pkey
            primary key,
    tenant_id integer not null,
    text      text    not null
);

alter table public.user_info
    owner to postgres;

create policy tenant_policy on public.user_info
    as permissive
    for all
    to public
    using (SELECT (user_info.tenant_id = ANY ((current_setting('app.user_id'::text))::integer [])));

grant delete, insert, references, select, trigger, truncate, update on public.user_info to app_user;
