package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.PostListener;
import sample.listener.StringListener;

public class LikeListener implements PostListener {
    TweetController tweetController;

    public LikeListener(){
        tweetController = new TweetController();
    }


    @Override
    public void listen(Post post, String command) {
        if (command.equals("Like")){
            if (new IsLikeButtonPressedListener().isItPressed(post))
                tweetController.dislike(post);
            else
                tweetController.like(post);
        }
    }
}
