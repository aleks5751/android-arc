package io.neverstoplearning.advancedandroid.details;

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
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(ContributorRenderer renderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
