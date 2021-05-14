package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.IsPostButtonPressedListener;

public class IsRetweetButtonPressedListener implements IsPostButtonPressedListener {
    TweetController tweetController;

    public IsRetweetButtonPressedListener(){
        tweetController = new TweetController();
    }

    @Override
    public boolean isItPressed(Post post) {
        return tweetController.checkIfUserRetweetedPost(post);
    }

    public String getTextOfLabel(Post post){
        if (isItPressed(post)) return "Undo Retweet";
        else return "Retweet";
    }
}
