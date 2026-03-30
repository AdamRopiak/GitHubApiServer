package com.githubapiserver.localdbgithub.infrastructure.dto.response;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;

public record PatchLocalRepoResponseDto(ReposDto updatedRepo) {
}
