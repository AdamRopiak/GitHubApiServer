package com.githubapiserver.localdbgithub.infrastructure.dto.response;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import org.springframework.http.HttpStatus;

public record DeleteLocalRepoResponseDto(String message, HttpStatus status) {
}
