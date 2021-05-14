package sample;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.config.DBConfig;
import sample.model.Model;
import sample.view.MainPage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new MainPage(stage);
//        Parent root = FXMLLoader.load(getClass().getResource("v.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File(new DBConfig().getLastID()));
            Model.setLastId(scanner.nextInt());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e){
            Model.setLastId(0);
        }
        launch(args);
    }
}
