package io.neverstoplearning.advancedandroid.details;

import javax.inject.Inject;
import javax.inject.Named;

import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.di.ForScreen;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;
import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.neverstoplearning.poweradapter.adapter.RecyclerAdapter;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope
class RepoDetailsPresenter {

    private ScreenNavigator screenNavigator;

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource contributorDataSource,
            ScreenNavigator screenNavigator) {

        this.screenNavigator = screenNavigator;

        disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(contributorDataSource::setData)
                .subscribe(viewModel.contributorsLoaded(), throwable -> {
                    // We handle logging in the view model
                }));
    }

    void onContributorClicked(Contributor contributor) {
        screenNavigator.goToContributorDetails(contributor.login());
    }
}
