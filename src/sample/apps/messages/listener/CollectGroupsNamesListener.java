package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.listener.CollectNamesListener;

import java.util.LinkedList;

public class CollectGroupsNamesListener implements CollectNamesListener {
    private final MessagesController controller;

    public CollectGroupsNamesListener(){
        controller = new MessagesController();
    }

    @Override
    public LinkedList<String> colletNames() {
        return controller.collectGroupsNames();
    }
}
