package sample.apps.post.model;

import sample.model.Model;

public class Like extends Model {

    private int post;
    private int liker;

    public Like(int post, int liker) {
        this.post = post;
        this.liker = liker;
    }

    public int getPost() {
        return post;
    }

    public int getLiker() {
        return liker;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public void setLiker(int liker) {
        this.liker = liker;
    }
}
