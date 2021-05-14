package sample.listener;

import sample.apps.messages.model.Group;

public interface GroupListener {
    void listen(Group group, String command);
}
