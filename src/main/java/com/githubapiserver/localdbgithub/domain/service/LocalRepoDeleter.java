package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class LocalRepoDeleter {
    private final LocalRepoRepository localRepoRepository;
    private final LocalRepoRetriever songRetriever;

    public LocalRepoDeleter(LocalRepoRepository localRepoRepository, LocalRepoRetriever songRetriever) {
        this.localRepoRepository = localRepoRepository;
        this.songRetriever = songRetriever;
    }

    public void deteById(Long id) {
        songRetriever.existsById(id);
        log.info("Deleting repo: " + id);
        localRepoRepository.deleteById(id);
    }
}
