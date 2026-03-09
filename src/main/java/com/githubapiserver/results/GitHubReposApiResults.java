package com.githubapiserver.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.githubapiserver.response.Owner;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubReposApiResults(String name,
                                    Owner owner,
                                    String default_branch) {
}
