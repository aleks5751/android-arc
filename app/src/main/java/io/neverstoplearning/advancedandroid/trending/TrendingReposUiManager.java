package io.neverstoplearning.advancedandroid.trending;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.ScreenLifecycleTask;
import io.neverstoplearning.advancedandroid.util.ButterKnifeUtils;

@ScreenScope
public class TrendingReposUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;

    @Inject
    TrendingReposUiManager() {

    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_trending);
    }

    @Override
    public void onExitScope() {
        ButterKnifeUtils.unbind(unbinder);
    }
}
