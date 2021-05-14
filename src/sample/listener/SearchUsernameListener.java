package sample.listener;

import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.model.User;

public interface SearchUsernameListener {
    User search(String username) throws UserDoesNotExistException;
}
