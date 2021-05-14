package sample.apps.post.model;

import sample.model.Model;

public abstract class Media extends Model {
    private String imagePath;
    private boolean hasImage;

    private final int sender;
    private String content;

    public Media(int sender, String content){
        super();
        this.sender = sender;
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        this.hasImage = true;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public int getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
