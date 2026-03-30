package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoNotFoundException;
import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LocalRepoRetriever {

    private final LocalRepoRepository localRepoRepository;

    public LocalRepoRetriever(LocalRepoRepository localRepoRepository) {
        this.localRepoRepository = localRepoRepository;
    }

    public List<LocalRepoEntity> findAll(Pageable pageable) {
        log.info("Retrieve all repos");
        return localRepoRepository.findAll(pageable);
    }

    public LocalRepoEntity findLocalGitGubRepoById(Long id) {
        return localRepoRepository.findLocalGitGubRepoById(id)
                .orElseThrow( () -> new LocalRepoNotFoundException("Repo with id: " + id + " not found"));
    }

    public List<LocalRepoEntity> findLocalRepoByUserName(String userName){
        log.info("Getting all repo for user: " + userName);
        return localRepoRepository.findAllByOwnerLogin(userName);
    }

    public void existsById(Long id) {
        if(!localRepoRepository.existsById(id)){
            throw new LocalRepoNotFoundException("Repo with id: " + id + " not found");
        }

    }
}
