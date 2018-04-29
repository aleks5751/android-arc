package io.neverstoplearning.advancedandroid.trending;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.ScreenLifecycleTask;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.neverstoplearning.poweradapter.item.ItemRenderer;
import io.neverstoplearning.poweradapter.item.RecyclerItem;
import io.neverstoplearning.poweradapter.item.RenderKey;

@Module
public abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(TrendingReposUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Repo")
    abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
