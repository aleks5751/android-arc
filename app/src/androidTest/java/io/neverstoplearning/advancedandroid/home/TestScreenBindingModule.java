package io.neverstoplearning.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import io.neverstoplearning.advancedandroid.details.RepoDetailsComponent;
import io.neverstoplearning.advancedandroid.details.RepoDetailsController;
import io.neverstoplearning.advancedandroid.di.ControllerKey;
import io.neverstoplearning.advancedandroid.trending.TrendingReposComponent;
import io.neverstoplearning.advancedandroid.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingReposComponent.class,
        RepoDetailsComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(RepoDetailsComponent.Builder builder);
}
