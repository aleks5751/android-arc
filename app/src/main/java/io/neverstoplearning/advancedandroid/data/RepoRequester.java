package io.neverstoplearning.advancedandroid.data;

import java.util.List;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.advancedandroid.model.ContributorDetails;
import io.neverstoplearning.advancedandroid.model.Repo;
import io.reactivex.Single;

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service) {
        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos);
    }

    Single<Repo> getRepo(String repoOwner, String repoName) {
        return service.getRepo(repoOwner, repoName);
    }

    Single<List<Contributor>> getContributors(String url) {
        return service.getContributors(url);
    }

    Single<ContributorDetails> getContributorDetails(String userName) {
        return service.getContributorDetails(userName);
    }
}
