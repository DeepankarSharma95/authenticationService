package com.pavoindus.authentication.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "authentication_id", nullable = false)
    private Authentication authentication;
    private String firstName;
    private String lastName;
    private Date whenCreated;

    public Member() {
    }

    public Member(Authentication authentication, String firstName, String lastName) {
        this.authentication = authentication;
        this.firstName = firstName;
        this.lastName = lastName;
        this.whenCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }
}
