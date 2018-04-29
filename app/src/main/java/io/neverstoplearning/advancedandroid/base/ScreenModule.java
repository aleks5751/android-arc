package io.neverstoplearning.advancedandroid.base;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;
import io.neverstoplearning.advancedandroid.di.ForScreen;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;
import io.neverstoplearning.advancedandroid.lifecycle.ScreenLifecycleTask;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
