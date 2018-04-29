package io.neverstoplearning.advancedandroid.contributordetails;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.neverstoplearning.advancedandroid.lifecycle.ScreenLifecycleTask;

/**
 * Created by aleksKrause on 03.04.2018.
 */

@Module
public abstract class ContributorDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUIManagerTask(ContributorDetailsUIManager uiManager);
}
