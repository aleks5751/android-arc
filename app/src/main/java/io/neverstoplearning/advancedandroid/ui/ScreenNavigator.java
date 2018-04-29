package io.neverstoplearning.advancedandroid.ui;

public interface ScreenNavigator {

    boolean pop();

    void goToRepoDetails(String repoOwner, String repoName);

    void goToContributorDetails(String userName);
}
