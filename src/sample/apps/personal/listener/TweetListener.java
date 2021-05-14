package sample.apps.personal.listener;

import sample.apps.personal.controller.PersonalController;
import sample.listener.StringListener;

public class TweetListener {
    private final PersonalController controller;

    public TweetListener(){
        controller = new PersonalController();
    }

    public void listen(String content, String imagePath) {
        controller.tweet(content, imagePath);
    }
}
