package sample.apps.settings.conroller;

import sample.apps.authentication.exception.WrongPasswordException;
import sample.apps.authentication.model.LastSeenSetting;
import sample.apps.authentication.model.User;
import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.apps.messages.model.Messages;
import sample.apps.notifications.model.Request;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Like;
import sample.apps.post.model.Post;
import sample.apps.post.model.Tweet;
import sample.apps.settings.event.ChangePasswordEvent;
import sample.controller.Controller;
import sample.view.MainPageListener;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class SettingsController extends Controller {

    public void changePassword(ChangePasswordEvent changePasswordEvent) throws WrongPasswordException {
        if (user.getPassword().equals(changePasswordEvent.getOldPassword())){
            user.setPassword(changePasswordEvent.getNewPassword());
            context.getUsers().update(user);
        } else {
            throw new WrongPasswordException();
        }
    }

    public void changeLastSeenSettings(LastSeenSetting lastSeenSetting){
        user.setLastSeenSetting(lastSeenSetting);
        context.getUsers().update(user);
    }

    public void changePrivacySettings(boolean isPrivate){
        user.setPrivate(isPrivate);
        context.getUsers().update(user);
    }

    public void deactivateAccount(){
        user.setInactive(true);
        context.getUsers().update(user);
    }

    public void logout(){
        user.setOnline(false);
        user.setLastSeen(LocalDateTime.now());
        context.getUsers().update(user);
        Controller.setUser(null);
        new MainPageListener().listen("logout");
    }

    public void deleteAccount(){
        deleteTweets();
        deleteComments();
        deleteLikes();
        deleteRetweets();
        deleteFollowers();
        deleteFollowing();
        deleteRequests();
        deleteUserMessages();
        deleteGroupMessages();
        logout();
    }

    private void deleteTweets(){
        LinkedList<Tweet> tweets = context.getTweets().allTweetsOfUser(user);
        for (Tweet tweet : tweets) {
            deletePost(tweet);
            context.getTweets().remove(tweet);
        }
    }

    private void deleteComments(){
        LinkedList<Comment> comments = context.getPosts().getComments().allCommentsOfUser(user);
        for (Comment comment : comments) {
            deletePost(comment);
            context.getPosts().getComments().remove(comment);
        }
    }

    private void deletePost(Post post){
        deleteLikesOfPost(post);
        deleteRetweetsOfPost(post);
        deleteCommentsOfPost(post);
    }

    private void deleteLikesOfPost(Post post){
        LinkedList<Like> likes = context.getLikes().likesOfPost(post);
        for (Like like : likes) {
            deleteLike(like, post);
        }
    }

    public void deleteLike(Like like, Post post){
        post.getLikes().remove(Integer.valueOf(like.getId()));
        context.getPosts().update(post);
        context.getLikes().remove(like);
    }

    private void deleteRetweetsOfPost(Post post){
        for (int i = 0; i < post.getRetweets().size(); i++) {
            int userID = post.getRetweets().get(i);
            User user1 = context.getUsers().get(userID);
            user1.getTweets().remove(Integer.valueOf(post.getId()));
            post.getRetweets().remove(Integer.valueOf(userID));
            context.getUsers().update(user1);
            i--;
        }
    }

    private void deleteCommentsOfPost(Post post){
        LinkedList<Comment> comments = context.getPosts().getCommentsOfPost(post);
        for (Comment comment : comments) {
            deleteCommentsOfPost(post);
        }
        deletePost(post);
    }

    private void deleteLikes(){
        LinkedList<Like> likes = context.getLikes().likesOfUser(user);
        for (Like like : likes) {
            deleteLike(like, context.getPosts().get(like.getPost()));
        }
    }

    public void deleteRetweets(){
        LinkedList<Post> posts = context.getPosts().all();
        for (Post post : posts) {
            for (int i = 0; i < post.getRetweets().size(); i++) {
                if (post.getRetweets().get(i) == user.getId()){
                    post.getRetweets().remove(Integer.valueOf(user.getId()));
                    break;
                }
            }
        }
    }

    private void deleteFollowers(){
        LinkedList<User> followers = context.getUsers().followersOfUser(user);
        for (User user1 : followers) {
            user1.getFollowing().remove(Integer.valueOf(user.getId()));
            context.getUsers().update(user1);
        }
    }

    private void deleteFollowing(){
        LinkedList<User> following = context.getUsers().followingOfUser(user);
        for (User user1 : following) {
            user1.getFollowers().remove(Integer.valueOf(user.getId()));
            context.getUsers().update(user1);
        }
    }

    private void deleteRequests(){
        deleteListOfRequests(context.getRequests().allReceivedRequests(user));
        deleteListOfRequests(context.getRequests().allSentRequest(user));
    }

    public void deleteListOfRequests(LinkedList<Request> requests){
        for (Request request : requests) {
            context.getRequests().remove(request);
        }
    }

    public void deleteUserMessages(){
        LinkedList<Messages> usersMessages = context.getUserMessages().allMessagesOfUser(user);
        for (Messages userMessages : usersMessages) {
            LinkedList<Message> messages = new MessagesController().collectUserMessages(userMessages);
            for (Message message : messages) {
                context.getMessages().remove(message);
            }
            User user1 = context.getUsers().get(userMessages.getUser2(user.getId()));
            user1.getMessages().remove(Integer.valueOf(userMessages.getId()));
            context.getUsers().update(user1);
            context.getUserMessages().remove(userMessages);
        }
    }

    private void deleteGroupMessages(){
        LinkedList<Group> groups = context.getGroups().allGroupsOfUser(user);
        for (Group group : groups) {
            LinkedList<Message> messages = new MessagesController().collectGroupMessages(group);
            for (Message message : messages) {
                if (message.getSender() == user.getId()){
                    group.getMessages().remove(Integer.valueOf(message.getId()));
                    context.getMessages().remove(message);
                }
            }
            context.getGroups().update(group);
        }
    }
}
