package com.githubapiserver.localdbgithub.infrastructure.dto.response;

import java.util.List;

public record GetAllLocalReposResponseDto(List<ReposDto> repos) {
}
