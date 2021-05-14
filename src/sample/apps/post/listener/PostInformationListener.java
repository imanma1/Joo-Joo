package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.event.PostInformationEvent;
import sample.apps.post.model.Post;
import sample.listener.CollectPostInformationListener;

public class PostInformationListener implements CollectPostInformationListener {
    private final TweetController tweetController;

    public PostInformationListener(){
        tweetController = new TweetController();
    }

    @Override
    public PostInformationEvent collectPostInformation(Post post) {
        return tweetController.collectInformation(post);
    }
}
