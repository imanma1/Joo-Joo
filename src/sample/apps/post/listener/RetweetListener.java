package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.exception.AccountIsPrivateException;
import sample.apps.post.model.Post;
import sample.listener.PostListener;

public class RetweetListener implements PostListener {
    private final TweetController tweetController;

    public RetweetListener(){
        tweetController = new TweetController();
    }

    @Override
    public void listen(Post post, String command) throws AccountIsPrivateException {
        if (command.equals("Retweet")){
            if (new IsRetweetButtonPressedListener().isItPressed(post))
                tweetController.undoRetweet(post);
            else
                tweetController.retweet(post);
        }
    }
}
