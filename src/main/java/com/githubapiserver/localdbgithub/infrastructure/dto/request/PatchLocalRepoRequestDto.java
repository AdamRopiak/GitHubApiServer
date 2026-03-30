package com.githubapiserver.localdbgithub.infrastructure.dto.request;

public record PatchLocalRepoRequestDto(String ownerLogin, String repoName) {
}
