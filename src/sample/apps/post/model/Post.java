package sample.apps.post.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Post extends Media{

    private final List<Integer> comments = new LinkedList<>();
    private final List<Integer> likes = new LinkedList<>();
    private final List<Integer> retweets = new LinkedList<>(); ///show people who retweets the post
    private final List<Integer> reports = new LinkedList<>();
    private boolean isReported;

    public Post(int sender, String content) {
        super(sender, content);
    }

    public List<Integer> getComments() {
        return comments;
    }

    public List<Integer> getLikes() {
        return likes;
    }

    public List<Integer> getRetweets() {
        return retweets;
    }

    public List<Integer> getReports() {
        return reports;
    }

    public boolean isReported() {
        return isReported;
    }

    public void setReported(boolean reported) {
        isReported = reported;
    }
}
