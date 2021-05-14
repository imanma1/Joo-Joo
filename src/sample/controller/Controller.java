package sample.controller;

import sample.apps.authentication.model.User;
import sample.db.Context;

public class Controller {
    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    protected static User user;

    protected Context context;
    public Controller() {
        instance = this;
        this.context = new Context();
    }

    public Context getContext() {
        return context;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Controller.user = user;
    }
}
