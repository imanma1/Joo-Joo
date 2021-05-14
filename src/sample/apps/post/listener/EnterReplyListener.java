package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.PostListener;

public class EnterReplyListener  {
    private final TweetController tweetController;

    public EnterReplyListener(){
        tweetController = new TweetController();
    }

    public void reply(Post post, String reply, String imagePath) {
        tweetController.reply(post, reply, imagePath);
    }
}
