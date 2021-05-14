package sample.listener;

import sample.apps.post.model.Comment;
import sample.apps.post.model.Post;

import java.util.LinkedList;

public interface CollectCommentsListener {
    LinkedList<Comment> collectReplies(Post post);
}
