package io.neverstoplearning.advancedandroid.base;

import javax.inject.Singleton;

import dagger.Component;
import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.data.TestRepoService;
import io.neverstoplearning.advancedandroid.data.TestRepoServiceModule;
import io.neverstoplearning.advancedandroid.networking.ServiceModule;
import io.neverstoplearning.advancedandroid.ui.TestActivityViewInterceptorModule;
import io.neverstoplearning.advancedandroid.ui.TestNavigationModule;
import io.neverstoplearning.advancedandroid.ui.TestScreenNavigator;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
        TestActivityViewInterceptorModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();
}
