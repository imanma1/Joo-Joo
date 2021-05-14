package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.PostListener;

public class ShareForGroupsListener implements PostListener {
    private final TweetController controller;

    public ShareForGroupsListener(){
        controller = new TweetController();
    }

    @Override /// command = groupName
    public void listen(Post post, String command) {
        controller.shareForGroup(post, command);
    }
}
