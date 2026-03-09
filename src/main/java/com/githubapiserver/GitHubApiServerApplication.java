package com.githubapiserver;

import com.githubapiserver.GitHubApiService.GitHubApiService;
import com.githubapiserver.results.GitHubReposApiResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class GitHubApiServerApplication {

    @Autowired
    GitHubApiService gitHubApiService;

    public static void main(String[] args) {
        SpringApplication.run(GitHubApiServerApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeQueryRequest() {
        List<GitHubReposApiResults> requestToExternalApi = gitHubApiService.getGitHubApiResults("adamropiak");
        System.out.println(requestToExternalApi);

    }

}
