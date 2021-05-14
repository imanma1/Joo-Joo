package sample.listener;

import sample.apps.post.model.Post;

import java.util.LinkedList;

public interface CollectPostsListener {
    LinkedList<Post> collectPosts();
}
