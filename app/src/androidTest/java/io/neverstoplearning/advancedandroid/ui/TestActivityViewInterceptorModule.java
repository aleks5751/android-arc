package io.neverstoplearning.advancedandroid.ui;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestActivityViewInterceptorModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
