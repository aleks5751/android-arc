package io.neverstoplearning.advancedandroid.contributordetails;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by aleksKrause on 02.04.2018.
 */

@AutoValue
abstract class ContributorDetailsState {

    abstract boolean loading();

    @Nullable
    abstract String name();

    @Nullable
    abstract String bio();

    @Nullable
    abstract String company();

    @Nullable
    abstract String email();

    @Nullable
    abstract Integer publicRepos();

    @Nullable
    abstract Integer followers();

    @Nullable
    abstract Integer following();

    @Nullable
    abstract String createdDate();

    @Nullable
    abstract String updatedDate();

    @Nullable
    abstract Integer errorRes();

    boolean isSuccess() {
        return errorRes() == null;
    }

    static Builder builder() {
        return new AutoValue_ContributorDetailsState.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {

        abstract Builder loading(boolean loading);

        abstract Builder name(String name);

        abstract Builder bio(String bio);

        abstract Builder company(String companyName);

        abstract Builder email(String email);

        abstract Builder publicRepos(Integer publicRepos);

        abstract Builder followers(Integer followers);

        abstract Builder following(Integer following);

        abstract Builder createdDate(String createdDate);

        abstract Builder updatedDate(String updatedDate);

        abstract Builder errorRes(Integer errorRes);

        abstract ContributorDetailsState build();
    }


}
