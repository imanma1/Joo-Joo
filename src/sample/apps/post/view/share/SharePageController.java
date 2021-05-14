package sample.apps.post.view.share;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sample.apps.post.model.Post;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class SharePageController extends PageController implements Initializable {
    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    @Override
    public void setPost(Post post) {
        super.setPost(post);
        users();
    }

    public void users(){
        ShareForUsersListPage usersListPage = new ShareForUsersListPage();
        usersListPage.getController().setPost(post);
        borderPane.setCenter(usersListPage.getPane());
    }

    public void groups(){
        ShareForGroupsListPage groupsListPage = new ShareForGroupsListPage();
        groupsListPage.getController().setPost(post);
        borderPane.setCenter(groupsListPage.getPane());
    }
}
