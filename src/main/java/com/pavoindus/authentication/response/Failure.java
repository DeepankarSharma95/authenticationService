package com.pavoindus.authentication.response;

import java.util.HashMap;

public class Failure extends APIResponse {

    private Object errors;

    public Failure() {
        this(ResponseStatus.Status.FAIL);
    }
    public Failure(ResponseStatus.Status status) {
        super(status);
        this.errors = new HashMap<>();
    }

    public Failure(Object errors) {
        super(ResponseStatus.Status.FAIL);
        this.errors = errors;
    }

    public Failure(ResponseStatus.Status status, Object errors) {
        super(status);
        this.errors = errors;
    }
}
