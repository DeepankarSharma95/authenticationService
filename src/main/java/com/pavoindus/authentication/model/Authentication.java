package com.pavoindus.authentication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Authentication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String authToken;
    private Date authTokenValidity;
    private Date whenCreated;

    public Authentication() {
    }

    public Authentication(String username, String password, String authToken, Date authTokenValidity) {
        this.username = username;
        this.password = password;
        this.authToken = authToken;
        this.authTokenValidity = authTokenValidity;
        this.whenCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Date getAuthTokenValidity() {
        return authTokenValidity;
    }

    public void setAuthTokenValidity(Date authTokenValidity) {
        this.authTokenValidity = authTokenValidity;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }
}
