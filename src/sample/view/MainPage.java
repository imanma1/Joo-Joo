package sample.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.apps.authentication.view.AccessPage;
import sample.apps.authentication.view.AccessPageController;

import java.util.LinkedList;

public class MainPage {

    private static MainPage instance;

    public static MainPage getInstance(){
        return instance;
    }

    private Stage stage;
    private BorderPane pane;
    private Page currentPage;
    private MenuSideBar menu;
    private final LinkedList<Page> stack = new LinkedList<>();

    public MainPage(Stage stage){
        instance = this;
        this.stage = stage;
        pane = new BorderPane();
        menu = new MenuSideBar();
        pane.setLeft(menu.getPane());
        pane.getLeft().setVisible(false);
        this.currentPage = new AccessPage();
        pane.setCenter(currentPage.getPane());
        stage.setScene(new Scene(pane));

        stage.setTitle("Joo Joo");
        stage.setResizable(false);
        stage.show();
    }

    public MenuSideBar getMenu() {
        return menu;
    }

    public void setMenu(MenuSideBar menu) {
        this.menu = menu;
    }

    public BorderPane getPane() {
        return pane;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public LinkedList<Page> getStack() {
        return stack;
    }

    public void setPage(Page page) {
        this.stack.add(page);
        this.currentPage = page;
        pane.setCenter(page.getPane());
    }

    public void back(){
        this.stack.removeLast();
        if (!stack.isEmpty()) {
            this.currentPage = this.stack.getLast();
            pane.setCenter(currentPage.getPane());
        }
    }
}
