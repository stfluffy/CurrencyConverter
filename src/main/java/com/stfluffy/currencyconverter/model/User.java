package com.stfluffy.currencyconverter.model;

import com.stfluffy.currencyconverter.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
@Getter
@Setter
public class User {

    /**
     * Identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Username.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * Password.
     */
    @Column(nullable = false)
    private String password;

    /**
     * User roles.
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
