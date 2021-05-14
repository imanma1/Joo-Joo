package sample.listener;

import sample.apps.authentication.model.User;

import java.util.LinkedList;

public interface CollectUsersListener {
    LinkedList<User> collectUsers();
}
