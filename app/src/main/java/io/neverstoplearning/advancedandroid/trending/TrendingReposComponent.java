package io.neverstoplearning.advancedandroid.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.neverstoplearning.advancedandroid.base.ScreenModule;
import io.neverstoplearning.advancedandroid.di.ScreenComponent;
import io.neverstoplearning.advancedandroid.di.ScreenScope;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        TrendingReposScreenModule.class,
})
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    }
}
