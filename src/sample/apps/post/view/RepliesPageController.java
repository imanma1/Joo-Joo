package sample.apps.post.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import sample.apps.image.controller.ImageController;
import sample.apps.post.listener.CollectRepliesListener;
import sample.apps.post.listener.EnterReplyListener;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Post;
import sample.listener.ReplyListener;
import sample.view.PageController;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class RepliesPageController extends PageController implements Initializable {

    EnterReplyListener replyListener;
    CollectRepliesListener collectRepliesListener;

    String imagePath = "";

    @FXML
    TextArea replyTxt;

    @FXML
    Button replyBtn;

    @FXML
    VBox box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        replyListener = new EnterReplyListener();
        collectRepliesListener = new CollectRepliesListener();
    }

    @Override
    public void setPost(Post post){
        this.post = post;
        setReplies();
    }

    private void setReplies(){
        LinkedList<Comment> comments = collectRepliesListener.collectReplies(post);
        for (Comment comment : comments) {
            TweetPage tweetPage = new TweetPage();
            tweetPage.getController().setPost(comment);
            box.getChildren().add(tweetPage.getPane());
        }
    }

    private String getReplyText(){
        return replyTxt.getText();
    }

    public void reply(){
        replyListener.reply(post, getReplyText(), imagePath);
        setReplies();
    }

    public void uploadImage(){
        imagePath = new ImageController().uploadImage();
    }
}
