package com.githubapiserver.localdbgithub.infrastructure.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteLocalRepoResponseDto(String message, HttpStatus status) {
}
