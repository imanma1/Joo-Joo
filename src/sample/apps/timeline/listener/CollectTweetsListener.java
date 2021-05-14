package sample.apps.timeline.listener;

import sample.apps.post.model.Post;
import sample.apps.timeline.controller.TimelineController;
import sample.listener.CollectPostsListener;

import java.util.LinkedList;

public class CollectTweetsListener implements CollectPostsListener {
    TimelineController timelineController;

    public CollectTweetsListener(){
        timelineController = new TimelineController();
    }

    @Override
    public LinkedList<Post> collectPosts() {
        return timelineController.collectTweets();
    }
}
