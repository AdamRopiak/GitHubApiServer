package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import com.githubapiserver.localdbgithub.infrastructure.dto.response.GitHubReposDto;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@Transactional
public class LocalRepoAdder {

    private final LocalRepoRepository localRepoRepository;


    public LocalRepoAdder(LocalRepoRepository localRepoRepository) {
        this.localRepoRepository = localRepoRepository;
    }

    public LocalRepoEntity addRepo(LocalRepoEntity newRepo) {
        log.info("Adding new repo: " + newRepo);
        LocalRepoEntity savedRepo = localRepoRepository.save(newRepo);
        return savedRepo;
    }

    public void saveGitHubReposToLocalDb(List<GitHubReposDto> repos){
        repos.forEach(repo ->{
            boolean exists = localRepoRepository.existsByOwnerLoginAndRepoName(
                    repo.ownerLogin(),
                    repo.repoName()
            );

            if(!exists){
                LocalRepoEntity newEntity = new LocalRepoEntity(repo.ownerLogin(), repo.repoName());
                localRepoRepository.save(newEntity);
                log.info("New repo: " + repo.repoName() + " for user: " + repo.ownerLogin() + " saved.");
            }else{
                log.info("Repo: " + repo.repoName() + " for user: " + repo.ownerLogin() + " already exists.");

            }
        });
    }

}
