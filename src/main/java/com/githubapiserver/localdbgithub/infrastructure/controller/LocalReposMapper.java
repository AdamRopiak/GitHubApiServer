package com.githubapiserver.localdbgithub.infrastructure.controller;

import com.githubapiserver.github.results.GitHubReposApiResults;
import com.githubapiserver.github.results.GitHubRepositoryResults;
import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.CreateLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.CreateLocalRepoResponseDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.PatchLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.PutLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public class LocalReposMapper {
    public static GetAllLocalReposResponseDto mapFromRepoEntityToGetAllLocalReposResponseDto(List<LocalRepoEntity> allRepos) {
        List<ReposDto> reposDtos = allRepos.stream()
                .map(repo -> LocalReposMapper.mapFromLocalRepoToReposDto(repo))
                .toList();
        return new GetAllLocalReposResponseDto(reposDtos);
    }

    private static ReposDto mapFromLocalRepoToReposDto(LocalRepoEntity repo) {
        return new ReposDto(repo.getRepoid(), repo.getOwnerLogin(), repo.getRepoName());
    }

    public static LocalRepoEntity mapFromCreateLocalRepoRequestDtoToRepoEntity(CreateLocalRepoRequestDto newRepo) {
        return new LocalRepoEntity(newRepo.ownerLogin(), newRepo.repoName());
    }

    public static CreateLocalRepoResponseDto mapFromRepoEntityToCreateLocalRepoResponseDro(LocalRepoEntity newRepo) {
        ReposDto reposDto = LocalReposMapper.mapFromLocalRepoToReposDto(newRepo);
        return new CreateLocalRepoResponseDto(reposDto);
    }


    public static LocalRepoEntity mapFromUpdateLocalRepoRequestDtoToRepoEntity(PutLocalRepoRequestDto repoRequest) {
        return new LocalRepoEntity(repoRequest.ownerLogin(), repoRequest.repoName());
    }

    public static PutLocalRepoResponseDto mapFromResponseEntityToPutSongResponseDto(LocalRepoEntity newRepo) {
        return new PutLocalRepoResponseDto(newRepo.getOwnerLogin(), newRepo.getRepoName());
    }

    public static DeleteLocalRepoResponseDto mapFromResponseEntitytoDeleteSongResponseDto(Long id) {
        return new DeleteLocalRepoResponseDto("Deteled repo with " + id, HttpStatus.OK);
    }

    public static LocalRepoEntity mapFromPatchLocalRepoRequestDtoToRepoEntity(PatchLocalRepoRequestDto repoRequest) {
        return new LocalRepoEntity(repoRequest.ownerLogin(), repoRequest.repoName());
    }

    public static PatchLocalRepoResponseDto mapFromRepoEntityToPatchLocalRepoRequestResponseDto(LocalRepoEntity updatedRepo) {
        ReposDto repo = LocalReposMapper.mapFromLocalRepoToReposDto(updatedRepo);
        return new PatchLocalRepoResponseDto(repo);
    }

    public static GetGithubRepoList mapFromGitHubReultsToDto(List<GitHubRepositoryResults> results){
        List<GitHubReposDto> repoLists = results.stream()
                .map(repo -> new GitHubReposDto(repo.ownerLogin(), repo.repoName()))
                .toList();
        return new GetGithubRepoList(repoLists);
    }


}
