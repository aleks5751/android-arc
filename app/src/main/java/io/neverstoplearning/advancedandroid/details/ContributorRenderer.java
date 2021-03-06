package io.neverstoplearning.advancedandroid.details;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.neverstoplearning.advancedandroid.R;
import io.neverstoplearning.advancedandroid.model.Contributor;
import io.neverstoplearning.poweradapter.item.ItemRenderer;

class ContributorRenderer implements ItemRenderer<Contributor> {

    private final Provider<RepoDetailsPresenter> presenterProvider;

    @Inject
    ContributorRenderer(Provider<RepoDetailsPresenter> presenterProvider) {
        this.presenterProvider = presenterProvider;
    }

    @Override
    public int layoutRes() {
        return R.layout.view_user_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, presenterProvider.get()));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull Contributor item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder {

        @BindView(R.id.tv_user_name) TextView usernameText;
        @BindView(R.id.iv_avatar) ImageView avatarImageView;
        private Contributor contributor;

        ViewBinder(View itemView, RepoDetailsPresenter repoDetailsPresenter) {
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if(contributor!=null){
                    repoDetailsPresenter.onContributorClicked(contributor);
                }
            });
        }

        void bind(Contributor contributor) {
            this.contributor = contributor;
            usernameText.setText(contributor.login());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl())
                    .into(avatarImageView);
        }
    }
}
