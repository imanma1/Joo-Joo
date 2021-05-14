package sample.listener;

import sample.apps.messages.event.GroupInformationEvent;
import sample.apps.messages.model.Group;

public interface CollectGroupInformationListener {
    GroupInformationEvent collectInformation(Group group);
}
