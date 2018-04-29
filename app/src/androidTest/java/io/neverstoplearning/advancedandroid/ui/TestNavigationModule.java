package io.neverstoplearning.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import io.neverstoplearning.advancedandroid.lifecycle.ActivityLifecycleTask;

@Module
public abstract class TestNavigationModule {

    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(TestScreenNavigator screenNavigator);
}
