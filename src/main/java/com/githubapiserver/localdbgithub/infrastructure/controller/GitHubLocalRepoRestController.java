package com.githubapiserver.localdbgithub.infrastructure.controller;

import com.githubapiserver.github.results.GitHubRepositoryResults;
import com.githubapiserver.github.service.GitHubApiService;
import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.service.*;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.CreateLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.CreateLocalRepoResponseDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.PatchLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.request.PutLocalRepoRequestDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.DeleteLocalRepoResponseDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.GetAllLocalReposResponseDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.PatchLocalRepoResponseDto;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.PutLocalRepoResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/repos")
@AllArgsConstructor
public class GitHubLocalRepoRestController {
    private final LocalRepoAdder localRepoAdder;
    private final LocalRepoDeleter localRepoDeleter;
    private final LocalRepoRetriever localRepoRetriever;
    private final LocalRepoUpdater localRepoUpdater;
    private final GitHubApiService gitHubApiService;
    private final SaveGitHubToLocalDb saveGitHubToLocalDb;

    @GetMapping
    public ResponseEntity<GetAllLocalReposResponseDto> findAllRepos(@PageableDefault(page = 0, size = 10) Pageable pageable){
        List<LocalRepoEntity> getAllLocalGitHubRepos = localRepoRetriever.findAll(pageable);
        GetAllLocalReposResponseDto response = LocalReposMapper.mapFromRepoEntityToGetAllLocalReposResponseDto(getAllLocalGitHubRepos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<GetAllLocalReposResponseDto> findReposByUserName(@PathVariable String userName){
        //List<GitHubRepositoryResults> repos = gitHubApiService.getGitHubApiResults(userName);
        //GetAllLocalReposResponseDto response = saveGitHubToLocalDb.syncRepos(userName, repos);
        List<LocalRepoEntity> getLocalRepoByUserName = localRepoRetriever.findLocalRepoByUserName(userName);
        GetAllLocalReposResponseDto response = LocalReposMapper.mapFromRepoEntityToGetAllLocalReposResponseDto(getLocalRepoByUserName);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateLocalRepoResponseDto> postNewLocalDbRepo(@RequestBody
                                                                             @Valid
                                                                         CreateLocalRepoRequestDto repo){
        LocalRepoEntity newRepoEntity = LocalReposMapper.mapFromCreateLocalRepoRequestDtoToRepoEntity(repo);
        LocalRepoEntity savedRepo = localRepoAdder.addRepo(newRepoEntity);
        CreateLocalRepoResponseDto response = LocalReposMapper.mapFromRepoEntityToCreateLocalRepoResponseDro(savedRepo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteLocalRepoResponseDto> removeLocalRepoFromDb(@RequestParam Long id){
        localRepoDeleter.deteById(id);
        DeleteLocalRepoResponseDto response = LocalReposMapper.mapFromResponseEntitytoDeleteSongResponseDto(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutLocalRepoResponseDto> updateLocalRepoDbById(@PathVariable Long id,
                                                                         @RequestBody
                                                                         @Valid
                                                                         PutLocalRepoRequestDto repoRequest){
        LocalRepoEntity newRepo = LocalReposMapper.mapFromUpdateLocalRepoRequestDtoToRepoEntity(repoRequest);
        localRepoUpdater.updateRepoById(id, newRepo);
        PutLocalRepoResponseDto response = LocalReposMapper.mapFromResponseEntityToPutSongResponseDto(newRepo);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchLocalRepoResponseDto> partiallyUpdateLocalRepoById(@PathVariable Long id,
                                                                                  @RequestBody
                                                                                  PatchLocalRepoRequestDto repoRequest){
        LocalRepoEntity updatedRepo = LocalReposMapper.mapFromPatchLocalRepoRequestDtoToRepoEntity(repoRequest);
        LocalRepoEntity savedRepo = localRepoUpdater.updatePartiallyRepoById(id, updatedRepo);
        PatchLocalRepoResponseDto response = LocalReposMapper.mapFromRepoEntityToPatchLocalRepoRequestResponseDto(savedRepo);
        return ResponseEntity.ok(response);

    }
}
