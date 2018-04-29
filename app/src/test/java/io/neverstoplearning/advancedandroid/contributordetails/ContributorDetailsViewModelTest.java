package io.neverstoplearning.advancedandroid.contributordetails;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.model.ContributorDetails;
import io.neverstoplearning.advancedandroid.testutils.TestUtils;

import static org.junit.Assert.*;

/**
 * Created by aleksKrause on 03.04.2018.
 */
public class ContributorDetailsViewModelTest {

    private ContributorDetailsViewModel contributorDetailsViewModel;

    private ContributorDetails contributorDetails = TestUtils.loadJson("mock/repos/contributors/contributor_details.json", ContributorDetails.class);
    @Before
    public void setUp() throws Exception {
        contributorDetailsViewModel = new ContributorDetailsViewModel();
    }

    @Test
    public void processContributorDetails() throws Exception {
        contributorDetailsViewModel.processContributorDetails().accept(contributorDetails);

        contributorDetailsViewModel.contributorDetails().test().assertValue(
          ContributorDetailsState.builder()
                .loading(false)
                .name("The Octocat")
                .bio(null)
                .company("GitHub")
                .email(null)
                .publicRepos(7)
                .followers(2208)
                .following(5)
                .createdDate("Jan 25, 2011")
                .updatedDate("Mar 23, 2018")
                .build()
        );
    }

    @Test
    public void contributorDetailsError() throws Exception {
        contributorDetailsViewModel.contributorDetailsError().accept(new IOException());

        contributorDetailsViewModel.contributorDetails().test().assertValue(
          ContributorDetailsState.builder()
                .loading(false)
                .errorRes(R.string.api_error_contributor_details)
                .build()
        );
    }

}