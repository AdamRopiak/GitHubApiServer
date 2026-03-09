package com.githubapiserver;

import com.githubapiserver.github.service.GitHubApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GitHubApiServerApplication {

    @Autowired
    GitHubApiService gitHubApiService;

    public static void main(String[] args) {
        SpringApplication.run(GitHubApiServerApplication.class, args);
    }

/*    @EventListener(ApplicationStartedEvent.class)
    public void makeQueryRequest() {
        List<GitHubRepositoryResults> requestToExternalApi = gitHubApiService.getGitHubApiResults("adamropiak");
        System.out.println(requestToExternalApi);

    }*/

}
