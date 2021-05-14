package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Post;
import sample.listener.IsPostButtonPressedListener;

public class IsLikeButtonPressedListener implements IsPostButtonPressedListener {
    TweetController tweetController;

    public IsLikeButtonPressedListener(){
        tweetController = new TweetController();
    }

    @Override
    public boolean isItPressed(Post post) {
        return tweetController.checkIfUserLikedPost(post);
    }

    public String getTextOfLabel(Post post){
        if (isItPressed(post)) return "Dislike";
        else return "Like";
    }
}
