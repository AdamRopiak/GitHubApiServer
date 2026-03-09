package com.githubapiserver.github.results;

import com.githubapiserver.github.buildrequestrecords.Branches;

import java.util.List;

public record GitHubRepositoryResults(String repoName,
                                      String ownerLogin,
                                      List<Branches> branches) {

}
