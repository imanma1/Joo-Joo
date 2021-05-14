package sample.view;

import javafx.scene.image.Image;
import sample.apps.authentication.model.User;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.apps.messages.model.Messages;
import sample.apps.notifications.model.Request;
import sample.apps.post.model.Post;

public abstract class PageController {
    protected User user;
    protected Post post;
    protected Request request;
    protected Group group;
    protected Messages messages;
    protected Message message;
    protected Image image;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
