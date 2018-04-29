package io.neverstoplearning.advancedandroid.di;

import dagger.android.AndroidInjector;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();
}
