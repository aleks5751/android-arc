package io.neverstoplearning.advancedandroid.data;

import java.util.List;

import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.advancedandroid.model.ContributorDetails;
import io.neverstoplearning.advancedandroid.model.Repo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

    @GET
    Single<List<Contributor>> getContributors(@Url String url);

    @GET("users/{username}")
    Single<ContributorDetails> getContributorDetails(@Path("username") String userName);
}
