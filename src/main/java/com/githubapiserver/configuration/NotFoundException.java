package com.githubapiserver.configuration;

public class NotFoundException extends RuntimeException {
    private final int status = 404;

    public NotFoundException(String message) {
        super(message);
    }
    public int getStatus() {
        return status;
    }
}
