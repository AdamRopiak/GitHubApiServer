package com.githubapiserver.github.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.githubapiserver.github.buildrequestrecords.Owner;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GitHubReposApiResults(String name,
                                    Owner owner,
                                    boolean fork) {
}
