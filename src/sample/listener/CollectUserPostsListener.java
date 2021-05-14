package sample.listener;

import sample.apps.authentication.model.User;
import sample.apps.post.model.Post;

import java.util.LinkedList;

public interface CollectUserPostsListener {
    LinkedList<Post> collectPosts(User user);
}
