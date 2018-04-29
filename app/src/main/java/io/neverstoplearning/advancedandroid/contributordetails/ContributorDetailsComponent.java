package io.neverstoplearning.advancedandroid.contributordetails;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.neverstoplearning.advancedandroid.base.ScreenModule;
import io.neverstoplearning.advancedandroid.di.ScreenScope;

/**
 * Created by aleksKrause on 02.04.2018.
 */
@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        ContributorDetailsScreenModule.class

})
public interface ContributorDetailsComponent extends AndroidInjector<ContributorDetailsController>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ContributorDetailsController> {

        @BindsInstance
        public abstract void bindRepoOwner(@Named("username") String userName);

        @Override
        public void seedInstance(ContributorDetailsController instance) {
            bindRepoOwner(instance.getArgs().getString(ContributorDetailsController.CONTRIBUTOR_USER_NAME));
        }
    }
}
