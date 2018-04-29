package io.neverstoplearning.advancedandroid.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;

import org.junit.Rule;

import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.data.TestRepoService;
import io.neverstoplearning.advancedandroid.home.MainActivity;
import io.neverstoplearning.advancedandroid.ui.TestScreenNavigator;

public abstract class ControllerTest {

    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestRepoService repoService = testRule.repoService;
    protected RepoRepository repoRepository = testRule.repoRepository;
    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;

    public ControllerTest() {
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    protected void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}
