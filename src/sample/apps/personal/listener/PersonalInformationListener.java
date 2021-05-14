package sample.apps.personal.listener;

import sample.apps.personal.controller.PersonalController;
import sample.apps.personal.event.PersonalInformationEvent;
import sample.listener.CollectPersonalInformationListener;

public class PersonalInformationListener implements CollectPersonalInformationListener {
    private final PersonalController personalController;

    public PersonalInformationListener(){
        personalController = new PersonalController();
    }

    @Override
    public PersonalInformationEvent collectInformation() {
        return personalController.collectInformation();
    }
}
