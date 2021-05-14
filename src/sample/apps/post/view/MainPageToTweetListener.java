package sample.apps.post.view;

import sample.apps.post.model.Post;
import sample.apps.post.view.share.SharePage;
import sample.apps.profile.view.ProfilePage;
import sample.controller.Controller;
import sample.listener.PostListener;
import sample.view.MainPage;

public class MainPageToTweetListener implements PostListener {
    private MainPage mainPage;

    public MainPageToTweetListener() {
        mainPage = MainPage.getInstance();
    }

    @Override
    public void listen(Post post, String command) {
        if (command.equals("replies")){
            RepliesPage repliesPage = new RepliesPage();
            repliesPage.getController().setPost(post);
            mainPage.setPage(repliesPage);
        } else if (command.equals("profile")){
            ProfilePage profilePage = new ProfilePage();
            profilePage.getController().setUser(Controller.getInstance().getContext().getUsers().get(post.getSender()));
            mainPage.setPage(profilePage);
        } else if (command.equals("share")){
            SharePage sharePage = new SharePage();
            sharePage.getController().setPost(post);
            mainPage.setPage(sharePage);
        }
    }
}
