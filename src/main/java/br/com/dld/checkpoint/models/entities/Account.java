package br.com.dld.checkpoint.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author David Duarte
 */
@Entity
@Getter
@Setter
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 20, unique = true)
    private String username;

    @Column
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean accountExpired;

    @Column(columnDefinition = "boolean default false")
    private boolean accountLocked;

    @Column(columnDefinition = "boolean default false")
    private boolean credentialsExpired;

    @Column(columnDefinition = "boolean default false")
    private boolean enabled;
    
    @Column
    private LocalDate creationDate;
    
    @Column(length = 20)
    private String activationCode;
}
