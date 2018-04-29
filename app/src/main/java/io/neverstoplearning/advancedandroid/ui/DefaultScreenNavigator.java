package io.neverstoplearning.advancedandroid.ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import io.neverstoplearning.advancedandroid.contributordetails.ContributorDetailsController;
import io.neverstoplearning.advancedandroid.details.RepoDetailsController;
import io.neverstoplearning.advancedandroid.di.ActivityScope;
import io.neverstoplearning.advancedandroid.lifecycle.ActivityLifecycleTask;

@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private Router router;

    @Inject
    DefaultScreenNavigator() {

    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        if (!(activity instanceof RouterProvider)) {
            throw new IllegalArgumentException("Activity must be instance of RouterProvider");
        }
        initWithRouter(((RouterProvider) activity).getRouter(), ((RouterProvider) activity).initialScreen());
    }

    void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        if (router != null) {
            router.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoName, repoOwner))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void goToContributorDetails(String userName) {
        if(router!=null){
            router.pushController(RouterTransaction.with(ContributorDetailsController.newInstance(userName))
            .pushChangeHandler(new FadeChangeHandler())
            .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        router = null;
    }
}
