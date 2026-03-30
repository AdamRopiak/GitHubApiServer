package com.githubapiserver.localdbgithub.domain.model;

public class LocalRepoNotFoundException extends RuntimeException {
    public LocalRepoNotFoundException(String message) {
        super(message);
    }
}
