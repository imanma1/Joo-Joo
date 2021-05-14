package sample.apps.explore.controller;

import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.model.User;
import sample.apps.post.model.Post;
import sample.controller.Controller;

import java.util.LinkedList;

public class ExploreController extends Controller {

    public User returnUser(String username) throws UserDoesNotExistException {
        for (User user : context.getUsers().all()) {
            if (user.getUsername().equals(username)) {
                if (!user.getBlacklist().contains(this.user.getId()) &&
                !user.isInactive())
                    return user;
            }
        }
        throw new UserDoesNotExistException();
    }

    public LinkedList<Post> collectPosts(){
        LinkedList<Post> posts = new LinkedList<>(context.getTweets().all());
        sortPosts(posts);
        deletePrivateAndMutedAndBlockedUsers(posts);
        return posts;
    }

    private void sortPosts(LinkedList<Post> posts){
        for (int i = 0; i < posts.size(); i++) {
            for (int j = i+1; j < posts.size(); j++) {
                Post tweet = posts.get(i);
                Post tweet1 = posts.get(j);
                if (tweet1.getLikes().size()>tweet.getLikes().size()) {
                    posts.set(i, tweet1);
                    posts.set(j, tweet);
                }
            }
        }
    }

    public void deletePrivateAndMutedAndBlockedUsers(LinkedList<Post> posts){
        for (int i = 0; i < posts.size(); i++) {
            int userID = posts.get(i).getSender();
            User user = context.getUsers().get(userID);
            if (this.user.getMuted().contains(userID) || this.user.getBlacklist().contains(userID) ||
                    (user.isPrivate() && !this.user.getFollowing().contains(userID)) ||
                    user.isInactive() || user.getBlacklist().contains(this.user.getId())){
                if (posts.get(i).isReported()){
                    posts.remove(posts.get(i));
                    i--;
                }
            }
        }
    }
}
