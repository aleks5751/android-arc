package io.neverstoplearning.advancedandroid.details;

import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.di.ScreenScope;
import io.neverstoplearning.advancedandroid.lifecycle.ScreenLifecycleTask;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.neverstoplearning.advancedandroid.util.ButterKnifeUtils;

@ScreenScope
public class RepoDetailsUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private final String repoName;
    private final ScreenNavigator screenNavigator;

    private Unbinder unbinder;

    @Inject
    RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {
        this.repoName = repoName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(repoName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
