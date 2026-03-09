package com.githubapiserver.client;


import com.githubapiserver.github.results.GitHubRepositoryResults;
import com.githubapiserver.github.service.GitHubApiService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/githubapi")
public class RestController {

    private final GitHubApiService gitHubApiService;

    public RestController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GitHubRepositoryResults> getGitHubApi(@PathVariable String userName) {
       return gitHubApiService.getGitHubApiResults(userName);
    }
}
