package com.githubapiserver.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubReposApiResponesToClient(String name,
                                             Owner owner,
                                             String default_branch) {
}
