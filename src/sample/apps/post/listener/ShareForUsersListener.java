package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.PostListener;

public class ShareForUsersListener implements PostListener {
    private final TweetController controller;

    public ShareForUsersListener(){
        controller = new TweetController();
    }


    @Override ///command = username
    public void listen(Post post, String command) {
        controller.shareForUser(post, command);
    }
}
