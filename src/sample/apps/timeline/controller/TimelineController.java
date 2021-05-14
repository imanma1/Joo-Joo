package sample.apps.timeline.controller;

import sample.apps.authentication.model.User;
import sample.apps.post.model.Post;
import sample.controller.Controller;

import java.util.LinkedList;

public class TimelineController extends Controller {

    public LinkedList<Post> collectTweets(){
        LinkedList<Post> posts = new LinkedList<>();
        LinkedList<User> following = context.getUsers().followingOfUser(user);
        for (User user1 : following) {
            if (!user.getMuted().contains(user1.getId())){
                for (int postID : user1.getTweets()) {
                    Post post = context.getPosts().get(postID);
                    if (!context.getUsers().get(post.getSender()).getBlacklist().contains(user.getId()) &&
                    !user.getBlacklist().contains(post.getSender()) &&
                    !context.getUsers().get(post.getSender()).isInactive()){
                        if (!post.isReported()){
                            posts.add(post);
                        }
                    }
                }
            }
        }
        sortTweets(posts);
        return posts;
    }

    public void sortTweets(LinkedList<Post> tweets){
        for (int i = 0; i < tweets.size(); i++) {
            for (int j = i+1; j < tweets.size(); j++) {
                Post tweet = tweets.get(i);
                Post tweet1 = tweets.get(j);
                if (tweet1.getCreateAt().isAfter(tweet.getCreateAt())) {
                    tweets.set(i, tweet1);
                    tweets.set(j, tweet);
                }
            }
        }
    }
}
