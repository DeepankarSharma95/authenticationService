package com.pavoindus.authentication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ApiKey implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String applicationName;
    private String apiKey;
    private Short tokenValidity;
    private Date whenCreated;

    public ApiKey() {
    }

    public ApiKey(String applicationName, String apiKey, Short tokenValidity) {
        this.applicationName = applicationName;
        this.apiKey = apiKey;
        this.tokenValidity = tokenValidity;
        this.whenCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Short getTokenValidity() {
        return tokenValidity;
    }

    public void setTokenValidity(Short tokenValidity) {
        this.tokenValidity = tokenValidity;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }
}
