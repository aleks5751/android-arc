package io.neverstoplearning.advancedandroid.trending;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.di.ForScreen;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;
import io.neverstoplearning.advancedandroid.model.Repo;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope
class TrendingReposPresenter {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;
    private final DisposableManager disposableManager;
    private final RecyclerDataSource dataSource;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource dataSource) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;
        loadRepos();
    }

    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .doOnSuccess(__ -> viewModel.reposUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
