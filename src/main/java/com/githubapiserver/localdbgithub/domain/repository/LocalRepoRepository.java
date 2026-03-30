package com.githubapiserver.localdbgithub.domain.repository;

import com.githubapiserver.localdbgithub.domain.model.LocalRepoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface LocalRepoRepository extends Repository<LocalRepoEntity, Long> {

    LocalRepoEntity save(LocalRepoEntity localRepoEntity);

    @Query("SELECT r FROM LocalRepoEntity r")
    List<LocalRepoEntity> findAll(Pageable pageable);

    @Query("SELECT r FROM LocalRepoEntity r WHERE r.repoid=:id")
    Optional<LocalRepoEntity> findLocalGitGubRepoById(Long id);

    @Query("SELECT r FROM  LocalRepoEntity r WHERE r.ownerLogin=:userName")
    List<LocalRepoEntity> findAllByOwnerLogin(String userName);

    boolean existsById(Long id);

    @Modifying
    @Query("DELETE FROM LocalRepoEntity r WHERE r.repoid=:id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE LocalRepoEntity r SET r.ownerLogin=:#{#newRepo.ownerLogin}, r.repoName=:#{#newRepo.repoName} WHERE r.repoid=:id")
    void updateRepoById(Long id, LocalRepoEntity newRepo);

    boolean existsByOwnerLoginAndRepoName(String ownerLogin, String repoName);
}
