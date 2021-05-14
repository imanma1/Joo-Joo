package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.IsPostButtonPressedListener;

public class IsReportButtonPressedListener implements IsPostButtonPressedListener {
    private final TweetController tweetController;

    public IsReportButtonPressedListener(){
        tweetController = new TweetController();
    }

    @Override
    public boolean isItPressed(Post post) {
        return tweetController.checkIfUserReportedPost(post);
    }
}
