package sample.apps.explore.listener;

import sample.apps.explore.controller.ExploreController;
import sample.apps.post.model.Post;
import sample.listener.CollectPostsListener;

import java.util.LinkedList;

public class CollectPopularTweetsListener implements CollectPostsListener {
    private final ExploreController controller;

    public CollectPopularTweetsListener(){
        controller = new ExploreController();
    }

    @Override
    public LinkedList<Post> collectPosts() {
        return controller.collectPosts();
    }
}
