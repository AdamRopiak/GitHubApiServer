package com.githubapiserver.GitHubApiService;

import com.githubapiserver.results.GitHubReposApiResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GitHubApiService {

    @Value("${external.api.server.url}")
    String externalApiServerUrl;
    @Value("${external.api.server.scheme}")
    String scheme;
    @Value("${external.api.server.port}")
    String port;
    @Value("${external.api.server.path}")
    String path;

    @Autowired
    WebClient webClient;

    public List<GitHubReposApiResults> getGitHubApiResults(String userName) {
        List<GitHubReposApiResults> externalApiServerUserReposResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(scheme)
                        .host(externalApiServerUrl)
                        .path(path)
                        .path("/" + userName)
                        .path("/repos")
                        .build())
                .retrieve()
                .bodyToFlux(GitHubReposApiResults.class)
                .collectList()
                .block();


        return externalApiServerUserReposResponse;
    }

}

