package sample.apps.personal.listener;

import sample.apps.personal.controller.PersonalController;
import sample.apps.post.model.Post;
import sample.listener.CollectPostsListener;

import java.util.LinkedList;

public class CollectPersonalPostsListener implements CollectPostsListener {
    private final PersonalController controller;

    public CollectPersonalPostsListener(){
        controller = new PersonalController();
    }

    @Override
    public LinkedList<Post> collectPosts() {
        return controller.collectPosts();
    }
}
