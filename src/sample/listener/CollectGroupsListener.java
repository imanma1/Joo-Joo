package sample.listener;

import sample.apps.messages.model.Group;

import java.util.LinkedList;

public interface CollectGroupsListener {
    LinkedList<Group> collectGroups();
}
