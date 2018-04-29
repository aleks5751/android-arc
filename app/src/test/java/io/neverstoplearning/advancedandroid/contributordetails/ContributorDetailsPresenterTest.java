package io.neverstoplearning.advancedandroid.contributordetails;

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
import io.neverstoplearning.advancedandroid.model.ContributorDetails;
import io.neverstoplearning.advancedandroid.testutils.TestUtils;
import io.neverstoplearning.advancedandroid.ui.ScreenNavigator;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksKrause on 03.04.2018.
 */
public class ContributorDetailsPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Mock
    RepoRepository repoRepository;

    @Mock
    ContributorDetailsViewModel contributorDetailsViewModel;

    @Mock
    Consumer<ContributorDetails> contributorDetailsConsumer;

    @Mock
    Consumer<Throwable> contributorDetailsErrorConsumer;

    private ContributorDetails contributorDetails = TestUtils.loadJson("mock/repos/contributors/contributor_details.json", ContributorDetails.class);
    private String userName = "octocat";
    private ContributorDetailsPresenter contributorDetailsPresenter;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(contributorDetailsViewModel.contributorDetailsError()).thenReturn(contributorDetailsErrorConsumer);
        when(contributorDetailsViewModel.processContributorDetails()).thenReturn(contributorDetailsConsumer);

        when(repoRepository.getContributorDetails(userName)).thenReturn(Single.just(contributorDetails));
    }

    @Test
    public void contributorDetails() throws Exception {
        initPresenter();

        verify(contributorDetailsConsumer).accept(contributorDetails);
    }

    @Test
    public void contributorDetailsError() throws Exception {
        Throwable throwable = new IOException();
        when(repoRepository.getContributorDetails(userName)).thenReturn(Single.error(throwable));
        initPresenter();
        verify(contributorDetailsErrorConsumer).accept(throwable);
    }

    @Test
    public void onRepoClicked(){
        List<Contributor> contributors = TestUtils.loadJson("mock/repos/contributors/get_contributors.json",
                Types.newParameterizedType(List.class, Contributor.class));
        Contributor mockedContributor = contributors.get(0);
        initPresenter();
    }

    private void initPresenter() {
        contributorDetailsPresenter = new ContributorDetailsPresenter(userName, repoRepository, contributorDetailsViewModel, Mockito.mock(DisposableManager.class));
    }
}