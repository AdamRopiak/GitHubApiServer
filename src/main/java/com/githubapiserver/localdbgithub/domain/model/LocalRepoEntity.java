package com.githubapiserver.localdbgithub.domain.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name="repositories")
public class LocalRepoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="repoid", nullable = false)
    private Long repoid;

    @Column(name="ownerlogin", nullable = false)
    private String ownerLogin;

    @Column(name="reponame", nullable = false)
    private String repoName;

    public LocalRepoEntity() {
        }

    public LocalRepoEntity(String ownerLogin, String repoName) {
        this.ownerLogin = ownerLogin;
        this.repoName = repoName;
    }

    public LocalRepoEntity(Long repoid, String ownerLogin, String repoName) {
        this.repoid = repoid;
        this.ownerLogin = ownerLogin;
        this.repoName = repoName;
    }
}
