package sample.apps.messages.event;

import java.io.File;
import java.util.EventObject;

public class MessageInformationEvent extends EventObject {
    private final String username;
    private final String content;
    private final String date;
    private final boolean isForwarded;
    private final String forwardedFrom;
    private final boolean hasImage;
    private final File imageFile;

    public MessageInformationEvent(Object source, String username, String content, String date, boolean isForwarded, String forwardedFrom, boolean hasImage, File imageFile) {
        super(source);
        this.username = username;
        this.content = content;
        this.date = date;
        this.isForwarded = isForwarded;
        this.forwardedFrom = forwardedFrom;
        this.hasImage = hasImage;
        this.imageFile = imageFile;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public String getForwardedFrom() {
        return forwardedFrom;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public File getImageFile() {
        return imageFile;
    }
}


