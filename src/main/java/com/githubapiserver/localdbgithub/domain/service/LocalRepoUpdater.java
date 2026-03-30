package com.githubapiserver.localdbgithub.domain.service;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import com.githubapiserver.localdbgithub.domain.repository.LocalRepoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
public class LocalRepoUpdater {

    private final LocalRepoRepository localRepoRepository;
    private final LocalRepoRetriever localRepoRetriever;

    public LocalRepoUpdater(LocalRepoRepository localRepoRepository, LocalRepoRetriever localRepoRetriever) {
        this.localRepoRepository = localRepoRepository;
        this.localRepoRetriever = localRepoRetriever;
    }

    public void updateRepoById(Long id, LocalRepoEntity newRepo){
        localRepoRetriever.existsById(id);
        log.info("You updated repo: " + newRepo);
        localRepoRepository.updateRepoById(id, newRepo);
    }
    public LocalRepoEntity updatePartiallyRepoById(Long id, LocalRepoEntity repoFromRequest){
        LocalRepoEntity repoToUpdate = localRepoRetriever.findLocalGitGubRepoById(id);
        LocalRepoEntity.LocalRepoEntityBuilder builder = LocalRepoEntity.builder();
        if(repoFromRequest.getRepoName()!=null){
            builder.repoName((repoFromRequest.getRepoName()));
            log.info("Partialy updated repo");
        }else{
            builder.repoName(repoToUpdate.getRepoName());
            log.info("Partially update owner");
        }
        if(repoFromRequest.getOwnerLogin()!=null){
            builder.ownerLogin((repoFromRequest.getOwnerLogin()));
        }else{
            builder.ownerLogin(repoToUpdate.getOwnerLogin());
        }
        LocalRepoEntity toSave = builder
                .repoid(repoToUpdate.getRepoid())
                .build();
        updateRepoById(id, toSave);
        return toSave;
    }


}
