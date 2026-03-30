package com.githubapiserver.localdbgithub.infrastructure.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateLocalRepoRequestDto (
        @NotNull(message = "Owner login can't be null")
        @NotEmpty(message = "Owner login can't be empty")
        String ownerLogin,
        @NotNull(message = "New repo name can't be null")
        @NotEmpty(message = "New repo name can't be empty")
        String repoName){
}
