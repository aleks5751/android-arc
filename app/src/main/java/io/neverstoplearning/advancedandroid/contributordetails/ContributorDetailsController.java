package io.neverstoplearning.advancedandroid.contributordetails;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;

import javax.inject.Inject;

import butterknife.BindView;
import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.base.BaseController;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by aleksKrause on 02.04.2018.
 */

public class ContributorDetailsController extends BaseController {

    public static final String CONTRIBUTOR_USER_NAME = "username";

    @Inject
    ContributorDetailsViewModel viewModel;
    @Inject
    ContributorDetailsPresenter presenter;
    @BindView(R.id.content)
    View vContent;
    @BindView(R.id.tv_contributor_name)
    TextView tvContributorName;
    @BindView(R.id.tv_bio)
    TextView tvBio;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.ll_publicRepos)
    View vPublicRepos;
    @BindView(R.id.tv_public_repos)
    TextView tvPublicRepos;
    @BindView(R.id.ll_followers)
    View vFollowers;
    @BindView(R.id.tv_followers)
    TextView tvFollowers;
    @BindView(R.id.ll_following)
    View vFollowing;
    @BindView(R.id.tv_following)
    TextView tvFollowing;
    @BindView(R.id.ll_creation_date)
    View vCreation;
    @BindView(R.id.tv_creation_date)
    TextView tvCreationDate;
    @BindView(R.id.ll_updated_date)
    View vUpdated;
    @BindView(R.id.tv_updated_date)
    TextView tvUpdatedDate;
    @BindView(R.id.loading_indicator)
    View vLoadingView;
    @BindView(R.id.tv_error)
    TextView tvError;

    public static Controller newInstance(String contributorUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(CONTRIBUTOR_USER_NAME, contributorUrl);
        return new ContributorDetailsController(bundle);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.contributorDetails()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(contributorDetails -> {
                    if (contributorDetails.loading()) {
                        vLoadingView.setVisibility(View.VISIBLE);
                        vContent.setVisibility(View.GONE);
                        tvError.setVisibility(View.GONE);
                        tvError.setText(null);
                    } else {
                        if (contributorDetails.isSuccess()) {
                            tvError.setText(null);
                        } else {
                            //noinspection ConstantConditions
                            tvError.setText(contributorDetails.errorRes());
                        }
                        vLoadingView.setVisibility(View.GONE);
                        vContent.setVisibility(contributorDetails.isSuccess() ? View.VISIBLE : View.GONE);
                        tvError.setVisibility(contributorDetails.isSuccess() ? View.GONE : View.VISIBLE);
                        tvContributorName.setText(contributorDetails.name());
                        tvBio.setText(contributorDetails.bio());
                        tvCompany.setText(contributorDetails.company());
                        tvEmail.setText(contributorDetails.email());
                        Integer publicRepos = contributorDetails.publicRepos();
                        if (publicRepos != null)
                            tvPublicRepos.setText(publicRepos.toString());
                        else vPublicRepos.setVisibility(View.GONE);
                        Integer followers = contributorDetails.followers();
                        if (followers != null) tvFollowers.setText(followers.toString());
                        else vFollowers.setVisibility(View.GONE);
                        Integer following = contributorDetails.following();
                        if (following != null) tvFollowing.setText(following.toString());
                        else vFollowing.setVisibility(View.GONE);
                        String createdDate = contributorDetails.createdDate();
                        if (createdDate != null) tvCreationDate.setText(createdDate);
                        else vCreation.setVisibility(View.GONE);
                        String updatedDate = contributorDetails.updatedDate();
                        if (updatedDate != null) tvUpdatedDate.setText(updatedDate);
                        else vUpdated.setVisibility(View.GONE);
                    }
                })
        };
    }

    public ContributorDetailsController() {
        super();
    }

    public ContributorDetailsController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_contributor_details;
    }

}
