package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

}
