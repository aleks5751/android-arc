package io.neverstoplearning.advancedandroid.contributordetails;

import com.jakewharton.rxrelay2.BehaviorRelay;

import org.threeten.bp.format.DateTimeFormatter;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.model.ContributorDetails;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by aleksKrause on 03.04.2018.
 */
@ScreenScope
class ContributorDetailsViewModel {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private final BehaviorRelay<ContributorDetailsState> contributorDetailsStateRelay = BehaviorRelay.create();

    @Inject
    ContributorDetailsViewModel(){
        contributorDetailsStateRelay.accept(ContributorDetailsState.builder().loading(true).build());
    }

    Observable<ContributorDetailsState> contributorDetails() {
        return contributorDetailsStateRelay;
    }

    Consumer<ContributorDetails> processContributorDetails() {
        return contributorDetailsState -> contributorDetailsStateRelay.accept(
                ContributorDetailsState.builder()
                .loading(false)
                .name(contributorDetailsState.name())
                .bio(contributorDetailsState.bio())
                .company(contributorDetailsState.company())
                .email(contributorDetailsState.email())
                .publicRepos(contributorDetailsState.publicRepos())
                .followers(contributorDetailsState.followers())
                .following(contributorDetailsState.following())
                .createdDate(contributorDetailsState.createdDate().format(DATE_FORMATTER))
                .updatedDate(contributorDetailsState.updatedDate().format(DATE_FORMATTER))
                .build());
    }

    Consumer<Throwable> contributorDetailsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading contributor details");
            contributorDetailsStateRelay.accept(
                    ContributorDetailsState.builder()
                    .loading(false)
                    .errorRes(R.string.api_error_contributor_details)
                    .build()
            );
        };
    }
}
