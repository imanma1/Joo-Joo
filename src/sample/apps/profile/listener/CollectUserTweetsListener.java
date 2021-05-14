package sample.apps.profile.listener;

import sample.apps.authentication.model.User;
import sample.apps.post.model.Post;
import sample.apps.profile.controller.ProfileController;
import sample.listener.CollectUserPostsListener;

import java.util.LinkedList;

public class CollectUserTweetsListener implements CollectUserPostsListener {
    private final ProfileController controller;

    public  CollectUserTweetsListener(){
        controller = new ProfileController();
    }

    @Override
    public LinkedList<Post> collectPosts(User user) {
        return controller.collectPosts(user);
    }
}
