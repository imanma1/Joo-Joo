package sample.apps.authentication.controller;

import sample.apps.authentication.event.AccessFormEvent;
import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.exception.WrongPasswordException;
import sample.apps.authentication.model.User;
import sample.controller.Controller;

public class AuthController extends Controller {

    public void register(AccessFormEvent formEvent) throws UserAlreadyExistException {
        if (!userExist(formEvent.getUsername())){
            User user = new User(formEvent.getUsername(), formEvent.getPassword());
            context.getUsers().add(user);
            setUser(user);
            user.setOnline(true);
        }else {
            throw new UserAlreadyExistException();
        }
    }

    public void login(AccessFormEvent formEvent) throws WrongPasswordException, UserDoesNotExistException {
        if (userExist(formEvent.getUsername())){
            if (!checkForPassword(context.getUsers().get(formEvent.getUsername()), formEvent.getPassword())){
                throw new WrongPasswordException();
            }
            setUser(context.getUsers().get(formEvent.getUsername()));
            user.setOnline(true);
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public boolean userExist(String username){
        for (User user : context.getUsers().all()) {
            if (username.equals(user.getUsername())) return true;
        }
        return false;
    }

    public boolean checkForPassword(User user, String password){
        return user.getPassword().equals(password);
    }
}
