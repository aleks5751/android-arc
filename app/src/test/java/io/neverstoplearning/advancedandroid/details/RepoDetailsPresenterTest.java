package io.neverstoplearning.advancedandroid.details;

import com.squareup.moshi.Types;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.neverstoplearning.advancedandroid.data.RepoRepository;
import io.neverstoplearning.advancedandroid.lifecycle.DisposableManager;
import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.advancedandroid.model.Repo;
import io.neverstoplearning.advancedandroid.testutils.TestUtils;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepoDetailsPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    private RepoDetailsPresenter presenter;
    private static final String OWNER = "owner";
    private static final String NAME = "name";

    @Mock
    RepoRepository repoRepository;
    @Mock
    RepoDetailsViewModel viewModel;
    @Mock
    Consumer<Repo> repoConsumer;
    @Mock
    Consumer<Object> contributorConsumer;
    @Mock
    Consumer<Throwable> detailErrorConsumer;
    @Mock
    Consumer<Throwable> contributorErrorConsumer;
    @Mock
    RecyclerDataSource dataSource;
    @Mock
    ScreenNavigator screenNavigator;
    private Repo repo = TestUtils.loadJson("mock/repos/get_repo.json", Repo.class);
    private List<Contributor> contributors = TestUtils.loadJson("mock/repos/contributors/get_contributors.json",
            Types.newParameterizedType(List.class, Contributor.class));
    private String contributorsUrl = repo.contributorsUrl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(viewModel.processRepo()).thenReturn(repoConsumer);
        when(viewModel.contributorsLoaded()).thenReturn(contributorConsumer);
        when(viewModel.detailsError()).thenReturn(detailErrorConsumer);
        when(viewModel.contributorsError()).thenReturn(contributorErrorConsumer);

        when(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.just(repo));
        when(repoRepository.getContributors(contributorsUrl)).thenReturn(Single.just(contributors));
    }

    @Test
    public void repoDetails() throws Exception {
        initPresenter();

        verify(repoConsumer).accept(repo);
    }

    @Test
    public void repoDetailsError() throws Exception {
        Throwable t = new IOException();
        when(repoRepository.getRepo(OWNER, NAME)).thenReturn(Single.error(t));
        initPresenter();

        verify(detailErrorConsumer).accept(t);
    }

    @Test
    public void repoContributors() throws Exception {
        initPresenter();

        verify(dataSource).setData(contributors);
    }

    @Test
    public void repoContributorsError() throws Exception {
        Throwable t = new IOException();
        when(repoRepository.getContributors(contributorsUrl)).thenReturn(Single.error(t));
        initPresenter();

        verify(contributorErrorConsumer).accept(t);
        // Verify that our repo details were still processed even though the contributors failed to load
        verify(repoConsumer).accept(repo);
    }

    @Test
    public void repoClickedListener() {
        List<Contributor> contributors = TestUtils.loadJson("mock/repos/contributors/get_contributors.json",
                Types.newParameterizedType(List.class, Contributor.class));
        Contributor mockedContributor = contributors.get(0);
        initPresenter();
        presenter.onContributorClicked(mockedContributor);
        verify(screenNavigator).goToContributorDetails(mockedContributor.login());
    }

    private void initPresenter() {
        presenter = new RepoDetailsPresenter(
                OWNER, NAME, repoRepository, viewModel, Mockito.mock(DisposableManager.class), dataSource, screenNavigator);
    }
}