package sample.apps.profile.controller;

import sample.apps.authentication.model.LastSeenSetting;
import sample.apps.authentication.model.User;
import sample.apps.explore.controller.ExploreController;
import sample.apps.notifications.model.Request;
import sample.apps.post.model.Post;
import sample.apps.profile.event.UserInformationEvent;
import sample.config.Config;
import sample.controller.Controller;

import java.io.File;
import java.util.LinkedList;

public class ProfileController extends Controller {

    public UserInformationEvent collectInformation(User user){
        String username = user.getUsername();
        String lastSeen = getLastSeen(user);
        String bio = user.getBio();
        int followers = user.getFollowers().size();
        int following = user.getFollowing().size();
        int posts = user.getTweets().size();
        boolean hasImage = user.getImagePath() != null;
        File imageFile = new File(Config.getInstance().getImagesConfigPath() + "\\" + user.getImagePath());
        return new UserInformationEvent(
                this,
                username,
                lastSeen,
                bio,
                followers,
                following,
                posts,
                hasImage,
                imageFile
        );
    }

    private String getLastSeen(User user){
        String lastSeen;
        if (user.getLastSeenSetting().equals(LastSeenSetting.PUBLIC)){
            lastSeen = getLastSeenDate(user);
        } else if (user.getLastSeenSetting().equals(LastSeenSetting.FOLLOWERS)){
            if (user.getFollowers().contains(this.user.getId())){
                lastSeen = getLastSeenDate(user);
            } else {
                lastSeen = "Last seen recently";
            }
        } else {
            lastSeen = "Last seen recently";
        }
        return lastSeen;
    }

    private String getLastSeenDate(User user){
        String lastSeen = "";
        if (user.isOnline()){
            lastSeen = "Online";
        } else {
            lastSeen = "Last seen at " +
                    user.getLastSeen().getHour() + ":" +
                    user.getLastSeen().getMinute();
        }
        return lastSeen;
    }

    public boolean checkIfUserRequestedThisAccount(User user){
        if (user.isPrivate()){
            LinkedList<Request> requests = context.getRequests().allReceivedRequests(user);
            for (Request request : requests) {
                if (request.getSender() == this.user.getId()) return true;
            }
        }
        return false;
    }

    public boolean checkIfUserFollowedThisAccount(User user){
        return this.user.getFollowing().contains(user.getId());
    }

    public boolean checkIfUserMutedThisAccount(User user){
        return this.user.getMuted().contains(user.getId());
    }

    public boolean checkIfUserBlockedThisAccount(User user){
        return this.user.getBlacklist().contains(user.getId());
    }

    public boolean checkIfAccountIsPublic(User user){
        return !user.isPrivate();
    }

    public void follow(User user) {
        this.user.getFollowing().add(user.getId());
        user.getFollowers().add(this.user.getId());
        user.getNotifications().add(this.user.getUsername() +
                " started following you");
        context.getUsers().update(this.user);
        context.getUsers().update(user);
    }

    public void unFollow(User user){
        this.user.getFollowing().remove(Integer.valueOf(user.getId()));
        user.getFollowers().remove(Integer.valueOf(this.user.getId()));
        context.getUsers().update(this.user);
        context.getUsers().update(user);
    }

    public void request(User user){
        Request request = new Request(user.getId(), this.user.getId());
        user.getRequests().add(request.getId());
        context.getRequests().add(request);
        context.getUsers().update(user);
    }

    public void undoRequest(User user){
        Request request = context.getRequests().getRequestOfUserToThisAccount(user, this.user);
        user.getRequests().remove(Integer.valueOf(request.getId()));
        context.getRequests().remove(request);
        context.getUsers().update(user);
    }

    public void mute(User user){
        this.user.getMuted().add(user.getId());
        context.getUsers().update(this.user);
    }

    public void unmute(User user){
        this.user.getMuted().remove(Integer.valueOf(user.getId()));
        context.getUsers().update(this.user);
    }

    public void block(User user){
        this.user.getBlacklist().add(user.getId());
        LinkedList<Post> posts = context.getPosts().getPostsOfProfile(user);
        for (Post post : posts) {
            if (post.getSender() == user.getId())
                this.user.getTweets().remove(Integer.valueOf(post.getId()));
        }
        unFollow(user);
    }

    public void unblock(User user){
        this.user.getBlacklist().remove(Integer.valueOf(user.getId()));
        context.getUsers().update(this.user);
    }

    public LinkedList<Post> collectPosts(User user){
        LinkedList<Post> posts = context.getPosts().getPostsOfProfile(user);
        new ExploreController().deletePrivateAndMutedAndBlockedUsers(posts);
        return posts;
    }


}
