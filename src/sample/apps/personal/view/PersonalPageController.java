package sample.apps.personal.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import sample.apps.personal.view.header.PersonalHeader;
import sample.apps.personal.view.tweets.TweetsPage;
import sample.view.PageController;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalPageController extends PageController implements Initializable {
    PersonalHeader personalHeader;
    TweetsPage tweetsPage;

    @FXML
    BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalHeader = new PersonalHeader();
        tweetsPage = new TweetsPage();
        borderPane.setTop(personalHeader.getPane());
        borderPane.setCenter(tweetsPage.getPane());
    }
}
