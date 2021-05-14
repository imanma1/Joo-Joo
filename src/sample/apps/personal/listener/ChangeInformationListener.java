package sample.apps.personal.listener;

import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.personal.controller.PersonalController;
import sample.apps.personal.event.FurtherPersonalInformationEvent;
import sample.listener.ChangePersonalInformationListener;

public class ChangeInformationListener implements ChangePersonalInformationListener {
    private final PersonalController controller;

    public ChangeInformationListener() {
        this.controller = new PersonalController();
    }

    @Override
    public void changeInformation(FurtherPersonalInformationEvent event) throws UserAlreadyExistException {
        if (!event.getUsername().isEmpty()){
            controller.changeUsername(event.getUsername());
        }
        if (!event.getName().isEmpty()){
            controller.changeName(event.getName());
        }
        if (!event.getBio().isEmpty()){
            controller.changeBio(event.getBio());
        }
        if (!event.getEmail().isEmpty()){
            controller.changeEmail(event.getEmail());
        }
        if (event.getBirth() != null){
            controller.changeBirth(event.getBirth());
        }
        if (event.getImagePath() != null){
            controller.changeAvatar(event.getImagePath());
        }
    }
}
