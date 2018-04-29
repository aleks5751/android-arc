package io.neverstoplearning.advancedandroid.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.threeten.bp.ZonedDateTime;

import io.neverstoplearning.poweradapter.item.RecyclerItem;

/**
 * Created by aleksKrause on 02.04.2018.
 */

@AutoValue
public abstract class ContributorDetails{

    public abstract long id();

    public abstract String login();

    public abstract String name();

    @Nullable
    public abstract String bio();

    @Nullable
    public abstract String company();

    @Nullable
    public abstract String email();

    @Nullable
    @Json(name = "public_repos")
    public abstract Integer publicRepos();

    @Nullable
    public abstract Integer followers();

    @Nullable
    public abstract Integer following();

    @Json(name = "created_at")
    public abstract ZonedDateTime createdDate();

    @Json(name = "updated_at")
    public abstract ZonedDateTime updatedDate();

    public static JsonAdapter<ContributorDetails> jsonAdapter(Moshi moshi) {
        return new AutoValue_ContributorDetails.MoshiJsonAdapter(moshi);
    }
}
