package io.neverstoplearning.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.neverstoplearning.advancedandroid.lifecycle.ActivityLifecycleTask;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
