package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.github.results.GitHubRepositoryResults;
import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
@Transactional
public class SaveGitHubToLocalDb {
    private final LocalRepoRepository localRepoRepository;

    public SaveGitHubToLocalDb(LocalRepoRepository localRepoRepository) {
        this.localRepoRepository = localRepoRepository;
    }

    public void saveGitHubToLocalDB(List<GitHubRepositoryResults> gitHubRepos){

        for(GitHubRepositoryResults repo : gitHubRepos){
            boolean exists = localRepoRepository.existsByOwnerLoginAndRepoName(
                    repo.ownerLogin(),
                    repo.repoName()
                    );
            if(!exists){
                LocalRepoEntity entity = LocalRepoEntity.builder()
                        .ownerLogin(repo.ownerLogin())
                        .repoName(repo.repoName())
                        .build();
                localRepoRepository.save(entity);
            }
        }

    }

    public void syncRepos(String ownerLogin, List<GitHubRepositoryResults> gitHubRepos){

        List<LocalRepoEntity> localRepos = localRepoRepository.findAllByOwnerLogin(ownerLogin);

        Set<String> gitGubNames = gitHubRepos.stream()
                .map(GitHubRepositoryResults::repoName)
                .collect(Collectors.toSet());

        Set<String> localNames = localRepos.stream()
                .map(LocalRepoEntity::getRepoName)
                .collect(Collectors.toSet());
        for(GitHubRepositoryResults repo : gitHubRepos){
            if(!localNames.contains(repo.repoName())){
                localRepoRepository.save(new LocalRepoEntity(repo.ownerLogin(), repo.repoName()));
            }
        }

        for(LocalRepoEntity repo : localRepos){
            if(!gitGubNames.contains(repo.getRepoName())){
                localRepoRepository.deleteById(repo.getRepoid());
            }
        }

    }
}
