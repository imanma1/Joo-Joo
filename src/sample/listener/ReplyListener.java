package sample.listener;

import sample.apps.post.model.Post;

public interface ReplyListener {
    void reply(Post post, String reply);
}
