package sample.apps.authentication.model;

import sample.model.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class User extends Model {

    private String imagePath;

    private String username;
    private String name;
    private String password;
    private LocalDate birth;
    private String email;
    private String bio;

    private final LinkedList<Integer> tweets = new LinkedList<>();
    private final LinkedList<Integer> comments = new LinkedList<>();
    private final LinkedList<Integer> following = new LinkedList<>();
    private final LinkedList<Integer> followers = new LinkedList<>();
    private final LinkedList<Integer> blacklist = new LinkedList<>();
    private final LinkedList<Integer> muted = new LinkedList<>();
    private final LinkedList<Integer> requests = new LinkedList<>();   ///type = user
    private final LinkedList<String> notifications = new LinkedList<>();
    private final LinkedList<Integer> messages = new LinkedList<>(); /// type = messages
    private final LinkedList<Integer> savedMessages = new LinkedList<>();
    private final LinkedList<Integer> groups = new LinkedList<>();

    private LocalDateTime lastSeen;
    private LastSeenSetting lastSeenSetting; ///0: everybody, 1: followers, 2: nobody
    private boolean isOnline;
    private boolean isInactive;
    private boolean isPrivate;

    public User(String username, String password){
        super();
        this.username = username;
        this.password = password;
        this.lastSeenSetting = LastSeenSetting.PUBLIC;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public LinkedList<Integer> getTweets() {
        return tweets;
    }

    public LinkedList<Integer> getComments() {
        return comments;
    }

    public LinkedList<Integer> getFollowing() {
        return following;
    }

    public LinkedList<Integer> getFollowers() {
        return followers;
    }

    public LinkedList<Integer> getBlacklist() {
        return blacklist;
    }

    public LinkedList<Integer> getMuted() {
        return muted;
    }

    public LinkedList<Integer> getRequests() {
        return requests;
    }

    public LinkedList<String> getNotifications() {
        return notifications;
    }

    public LinkedList<Integer> getMessages() {
        return messages;
    }

    public LinkedList<Integer> getSavedMessages() {
        return savedMessages;
    }

    public LinkedList<Integer> getGroups() {
        return groups;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public LastSeenSetting getLastSeenSetting() {
        return lastSeenSetting;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public boolean isInactive() {
        return isInactive;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setLastSeenSetting(LastSeenSetting lastSeenSetting) {
        this.lastSeenSetting = lastSeenSetting;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setInactive(boolean inactive) {
        isInactive = inactive;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
