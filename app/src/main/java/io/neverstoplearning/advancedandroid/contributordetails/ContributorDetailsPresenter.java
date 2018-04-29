package io.neverstoplearning.advancedandroid.contributordetails;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.di.ForScreen;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;
import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by aleksKrause on 03.04.2018.
 */

@ScreenScope
public class ContributorDetailsPresenter {

    @Inject
    ContributorDetailsPresenter(
            @Named("username") String userName,
            RepoRepository repoRepository,
            ContributorDetailsViewModel contributorDetailsViewModel,
            @ForScreen DisposableManager disposableManager) {
        disposableManager.add(repoRepository.getContributorDetails(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contributorDetailsViewModel.processContributorDetails(),
                        contributorDetailsViewModel.contributorDetailsError())
        );
    }
}
