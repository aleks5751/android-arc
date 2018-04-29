package io.neverstoplearning.advancedandroid.details;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.neverstoplearning.advancedandroid.base.ScreenModule;
import io.neverstoplearning.advancedandroid.di.ScreenComponent;
import io.neverstoplearning.advancedandroid.di.ScreenScope;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        RepoDetailsScreenModule.class,
})
public interface RepoDetailsComponent extends ScreenComponent<RepoDetailsController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailsController> {

        @BindsInstance
        public abstract void bindRepoOwner(@Named("repo_owner") String repoOwner);

        @BindsInstance
        public abstract void bindRepoName(@Named("repo_name") String repoName);

        @Override
        public void seedInstance(RepoDetailsController instance) {
            bindRepoOwner(instance.getArgs().getString(RepoDetailsController.REPO_OWNER_KEY));
            bindRepoName(instance.getArgs().getString(RepoDetailsController.REPO_NAME_KEY));
        }
    }
}
