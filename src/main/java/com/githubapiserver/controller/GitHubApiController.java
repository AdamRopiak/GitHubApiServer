package com.githubapiserver.controller;


import com.githubapiserver.GitHubApiService.GitHubApiService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubApiController {

    private final GitHubApiService gitHubApiService;

    public GitHubApiController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

  /*  @GetMapping("/api")
    public List<GitHubApiRespone>  getGitHubApi(@PathVariable String userName) {


        return null;
    }*/
}
