package sample.apps.post.event;

import java.io.File;
import java.util.EventObject;

public class PostInformationEvent extends EventObject {
    private final String tweet;
    private final String username;
    private final String date;
    private final int numberOfLikes;
    private final int numberOfRetweets;
    private final int numberOfReplies;
    private final boolean hasImage;
    private final File imageFile;
    private final boolean hasProfileImage;
    private final File profileImageFile;

    public PostInformationEvent(Object source, String tweet, String username, String date, int numberOfLikes, int numberOfRetweets, int numberOfReplies, boolean hasImage, File imageFile, boolean hasProfileImage, File profileImageFile) {
        super(source);
        this.tweet = tweet;
        this.username = username;
        this.date = date;
        this.numberOfLikes = numberOfLikes;
        this.numberOfRetweets = numberOfRetweets;
        this.numberOfReplies = numberOfReplies;
        this.hasImage = hasImage;
        this.imageFile = imageFile;
        this.hasProfileImage = hasProfileImage;
        this.profileImageFile = profileImageFile;
    }

    public String getTweet() {
        return tweet;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public int getNumberOfRetweets() {
        return numberOfRetweets;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public File getImageFile() {
        return imageFile;
    }

    public boolean isHasProfileImage() {
        return hasProfileImage;
    }

    public File getProfileImageFile() {
        return profileImageFile;
    }
}
