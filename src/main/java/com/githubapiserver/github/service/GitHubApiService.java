package com.githubapiserver.github.service;

import com.githubapiserver.configuration.NotFoundException;
import com.githubapiserver.github.buildrequestrecords.Branches;
import com.githubapiserver.github.results.GitHubRepositoryResults;
import com.githubapiserver.github.results.GitHubReposApiResults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Log4j2
public class GitHubApiService {

    @Value("${external.api.server.url}")
    String externalApiServerUrl;
    @Value("${external.api.server.scheme}")
    String scheme;
    @Value("${external.api.server.port}")
    String port;
    @Value("${external.api.server.userpath}")
    String userPath;
    @Value("${external.api.server.repospath}")
    String reposPath;
    @Value("${external.api.server.branchespath}")
    String branchesPath;

    @Autowired
    WebClient webClient;

    public List<GitHubRepositoryResults> getGitHubApiResults(String userName) {
        List<GitHubReposApiResults> externalApiServerUserReposResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(scheme)
                        .host(externalApiServerUrl)
                        .path(userPath)
                        .path("/" + userName)
                        .path("/" + reposPath)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new NotFoundException("User not Found")))
                .bodyToFlux(GitHubReposApiResults.class)
                .collectList()
                .doOnError(error -> log.error("ERROR: User " + userName + " not found"))
                .block();

        return externalApiServerUserReposResponse.stream()
                .filter(repo -> !repo.fork())
                        .map(repo -> repositoryMap(repo))
                        .toList();


    }
    private GitHubRepositoryResults repositoryMap(GitHubReposApiResults repo) {
        List<Branches> externalApiServerUserBranches = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(scheme)
                        .host(externalApiServerUrl)
                        .path(reposPath)
                        .path("/" + repo.owner().login())
                        .path("/" + repo.name())
                        .path(branchesPath)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Branches.class)
                .collectList()
                .block();
        return new GitHubRepositoryResults(
                repo.name(),
                repo.owner().login(),
                externalApiServerUserBranches
        );
    }

}

