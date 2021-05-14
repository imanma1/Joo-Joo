package sample.apps.profile.event;

import java.io.File;
import java.util.EventObject;

public class UserInformationEvent extends EventObject {
    private final String username;
    private final String lastSeen;
    private final String bio;
    private final int numberOfFollowers;
    private final int numberOfFollowing;
    private final int numberOfPosts;
    private final boolean hasImage;
    private final File imageFile;

    public UserInformationEvent(Object source, String username, String lastSeen, String bio, int numberOfFollowers, int numberOfFollowing, int numberOfPosts, boolean hasImage, File imageFile) {
        super(source);
        this.username = username;
        this.lastSeen = lastSeen;
        this.bio = bio;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfFollowing = numberOfFollowing;
        this.numberOfPosts = numberOfPosts;
        this.hasImage = hasImage;
        this.imageFile = imageFile;
    }

    public String getUsername() {
        return username;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getBio() {
        return bio;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public File getImageFile() {
        return imageFile;
    }
}
