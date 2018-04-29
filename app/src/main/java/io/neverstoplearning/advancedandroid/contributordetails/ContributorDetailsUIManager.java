package io.neverstoplearning.advancedandroid.contributordetails;

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

/**
 * Created by aleksKrause on 03.04.2018.
 */

@ScreenScope
public class ContributorDetailsUIManager extends ScreenLifecycleTask{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String userName;
    private final ScreenNavigator screenNavigator;

    private Unbinder unbinder;

    @Inject
    ContributorDetailsUIManager(@Named("username") String userName, ScreenNavigator screenNavigator) {
        this.userName = userName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(userName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view1 -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
