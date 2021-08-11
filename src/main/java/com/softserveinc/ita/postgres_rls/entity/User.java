package com.softserveinc.ita.postgres_rls.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_tenant",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tenant_id")
    )
    private List<Tenant> tenants;


}
