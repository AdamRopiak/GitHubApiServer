package com.githubapiserver.localdbgithub.infrastructure.dto.response;

public record ReposDto(Long repoid, String ownerLogin, String repoName) {
}
