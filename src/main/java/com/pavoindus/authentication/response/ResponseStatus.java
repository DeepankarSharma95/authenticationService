package com.pavoindus.authentication.response;

public class ResponseStatus {

    public enum Status {
        OK(200),
        FAIL(400),
        INVALID_APPLICATION(401),
        INVALID_AUTH_TOKEN(401),
        INVALID_CREDENTIALS(401),
        ACCOUNT_CLOSED(401),
        INVALID_MEMBER_LOGIN(401),
        MEMBER_ALREADY_EXISTS(400)
        ;

        int statusCode;
        Status(int statusCode) {
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
    }
}
