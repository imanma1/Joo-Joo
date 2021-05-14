package sample.apps.post.model;

import java.io.FileNotFoundException;

public class Comment extends Post {
    private final int upPost;

    public Comment(int sender, String content, int upPost) {
        super(sender, content);
        this.upPost = upPost;
    }

    public int getUpPost() {
        return upPost;
    }
}
