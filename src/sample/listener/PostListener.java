package sample.listener;

import sample.apps.post.exception.AccountIsPrivateException;
import sample.apps.post.model.Post;

public interface PostListener {
    void listen(Post post, String command) throws AccountIsPrivateException;
}
