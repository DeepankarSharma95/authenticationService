package com.pavoindus.authentication.response;

import java.util.HashMap;

public class Success extends APIResponse {

    private Object data;


    public Success() {
        super(ResponseStatus.Status.OK);
        this.data = new HashMap<String, Object>();
    }

    public Success(Object data) {
        super(ResponseStatus.Status.OK);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
