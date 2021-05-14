package sample.apps.post.listener;

import sample.apps.post.controller.TweetController;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Post;
import sample.listener.CollectCommentsListener;
import sample.listener.CollectPostsListener;

import java.util.LinkedList;

public class CollectRepliesListener implements CollectCommentsListener {
    TweetController tweetController;

    public CollectRepliesListener(){
        tweetController = new TweetController();
    }

    @Override
    public LinkedList<Comment> collectReplies(Post post) {
        return tweetController.collectReplies(post);
    }
}
