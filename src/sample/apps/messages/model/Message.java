package sample.apps.messages.model;

import sample.apps.post.model.Media;

public class Message extends Media {
    private final boolean isForwarded;
    private final int forwardedFrom; /// user
    private boolean isDeleted;

    public Message(int sender, String content) {
        super(sender, content);
        this.isForwarded = false;
        this.forwardedFrom = sender;
    }

    public Message(int sender, String content, int forwardedFrom){
        super(sender, content);
        this.isForwarded = true;
        this.forwardedFrom = forwardedFrom;
    }

    public boolean isForwarded() {
        return isForwarded;
    }

    public int getForwardedFrom() {
        return forwardedFrom;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
