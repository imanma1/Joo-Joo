package sample.db;

import sample.apps.authentication.model.User;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Post;
import sample.apps.post.model.Tweet;

import java.util.LinkedList;

public class PostDB implements DBSet<Post> {
    private final TweetDB tweets;
    private final CommentDB comments;

    public PostDB() {
        this.tweets = new TweetDB();
        this.comments = new CommentDB();
    }

    public TweetDB getTweets() {
        return tweets;
    }

    public CommentDB getComments() {
        return comments;
    }

    @Override
    public Post get(int id){
        Post post;
        try {
            post = tweets.get(id);
        } catch (Exception e){
            post = comments.get(id);
        }
        return post;
    }

    @Override
    public LinkedList<Post> all() {
        LinkedList<Post> posts = new LinkedList<>();
        posts.addAll(tweets.all());
        posts.addAll(comments.all());
        return posts;
    }

    @Override
    public void add(Post post) {
        try {
            tweets.add((Tweet) post);
        } catch (Exception e){
            comments.add((Comment) post);
        }
    }

    public LinkedList<Comment> getCommentsOfPost(Post post){
        LinkedList<Comment> commentsOfPost = new LinkedList<>();
        for (int commentID : post.getComments()) {
            commentsOfPost.add(comments.get(commentID));
        }
        return commentsOfPost;
    }

    public LinkedList<Post> getPostsOfProfile(User user){
        LinkedList<Post> posts = new LinkedList<>();
        for (int postID : user.getTweets()) {
            posts.add(get(postID));
        }
        return posts;
    }

    @Override
    public void remove(Post post){
        try {
            tweets.remove((Tweet) post);
        } catch (Exception e){
            comments.remove((Comment) post);
        }
    }

    @Override
    public void update(Post post) {
        remove(post);
        add(post);
    }

}
