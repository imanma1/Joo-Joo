package sample.listener;

import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.personal.event.FurtherPersonalInformationEvent;

public interface ChangePersonalInformationListener {
    void changeInformation(FurtherPersonalInformationEvent event) throws UserAlreadyExistException;
}
