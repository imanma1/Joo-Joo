package sample.apps.profile.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sample.apps.authentication.model.User;
import sample.apps.profile.view.header.ProfileHeaderPage;
import sample.apps.profile.view.tweets.TweetsPage;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController extends PageController implements Initializable {
    ProfileHeaderPage profileHeaderPage;
    TweetsPage tweetsPage;

    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileHeaderPage = new ProfileHeaderPage();
        tweetsPage = new TweetsPage();
        borderPane.setTop(profileHeaderPage.getPane());
        borderPane.setCenter(tweetsPage.getPane());
    }

    @Override
    public void setUser(User user) {
        super.setUser(user);
        profileHeaderPage.getController().setUser(user);
        tweetsPage.getController().setUser(user);
    }
}
