package io.neverstoplearning.advancedandroid.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import io.neverstoplearning.advancedandroid.base.TestApplication;
import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.data.TestRepoService;
import io.neverstoplearning.advancedandroid.ui.TestScreenNavigator;

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoService = TestApplication.getComponent().repoService();
        repoRepository = TestApplication.getComponent().repoRepository();
    }

    public void clearState() {
        repoService.clearErrorFlags();
        repoService.clearHoldFlags();
        repoRepository.clearCache();
    }
}
