package sample.apps.messages.controller;

import sample.apps.authentication.model.User;
import sample.apps.messages.event.GroupInformationEvent;
import sample.apps.messages.event.MessageInformationEvent;
import sample.apps.messages.event.MessagesInformationEvent;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.apps.messages.model.Messages;
import sample.config.Config;
import sample.controller.Controller;

import java.io.File;
import java.util.LinkedList;

public class MessagesController extends Controller {

    public MessageInformationEvent collectMessageInformation(Message message){
        String username = context.getUsers().get(message.getSender()).getUsername();
        String content = message.getContent();
        String date = message.getCreateAt().getHour() + ":" +
                message.getCreateAt().getMinute();
        boolean isForwarded = message.isForwarded();
        String forwardedFrom = context.getUsers().get(message.getForwardedFrom()).getUsername();
        boolean hasImage = message.isHasImage();
        File imageFile = new File(Config.getInstance().getImagesConfigPath() + "\\" + message.getImagePath());
        return new MessageInformationEvent(
                this,
                username,
                content,
                date,
                isForwarded,
                forwardedFrom,
                hasImage,
                imageFile
        );
    }

    public MessagesInformationEvent collectMessagesInformation(Messages messages){
        String username = context.getUsers().get(messages.getUser2(user.getId())).getUsername();
        LinkedList<Message> messages1 = collectUserMessages(messages);
        boolean isSeen = messages.getIsSeen().get(user.getId());
        return new MessagesInformationEvent(
                this,
                username,
                messages1,
                isSeen
        );
    }

    public GroupInformationEvent collectGroupInformation(Group group){
        String groupName = group.getName();
        LinkedList<User> users = collectGroupMembers(group);
        LinkedList<Message> messages = sortMessages(collectGroupMessages(group));
        boolean isSeen = group.getIsSeen().get(user.getId());
        return new GroupInformationEvent(
                this,
                groupName,
                users,
                messages,
                isSeen
        );
    }

    public LinkedList<Message> collectUserMessages(Messages messages){
        LinkedList<Message> messages1 = new LinkedList<>();
        for (int messageID : messages.getMessages()) {
            messages1.add(context.getMessages().get(messageID));
        }
        return messages1;
    }

    private LinkedList<User> collectGroupMembers(Group group){
        LinkedList<User> users = new LinkedList<>();
        for (int userID : group.getMembers()) {
            users.add(context.getUsers().get(userID));
        }
        return users;
    }

    public LinkedList<Message> collectGroupMessages(Group group){
        LinkedList<Message> messages = new LinkedList<>();
        for (int messageID : group.getMessages()) {
            Message message = context.getMessages().get(messageID);
            User user1 = context.getUsers().get(message.getSender());
            if (!user.getBlacklist().contains(user1.getId()) &&
            !user1.getBlacklist().contains(user.getId())){
                messages.add(context.getMessages().get(messageID));
            }
        }
        return messages;
    }

    private LinkedList<Message> sortMessages(LinkedList<Message> messages){
        for (int i = 0; i < messages.size(); i++) {
            for (int j = i+1; j < messages.size(); j++) {
                Message message1 = messages.get(i);
                Message message2 = messages.get(j);
                if (message1.getCreateAt().isAfter(message2.getCreateAt())){
                    messages.set(j, message1);
                    messages.set(i, message2);
                }
            }
        }
        return messages;
    }

    public LinkedList<Messages> collectUsersMessages(){
        LinkedList<Messages> messages = new LinkedList<>();
        messages.addAll(sortListOfUserMessages(collectUnreadMessages()));
        messages.addAll(sortListOfUserMessages(collectReadMessages()));
        return messages;
    }

    private LinkedList<Messages> collectUnreadMessages(){
        LinkedList<Messages> unreadMessages = new LinkedList<>();
        LinkedList<Messages> messages = context.getUserMessages().allMessagesOfUser(user);
        for (Messages userMessages : messages) {
            if (!userMessages.getIsSeen().get(user.getId())){
                User user1 = context.getUsers().get(userMessages.getUser2(user.getId()));
                if (!user1.getBlacklist().contains(user.getId()) &&
                !user.getBlacklist().contains(user1.getId())) {
                    unreadMessages.add(userMessages);
                }
            }
        }
        return unreadMessages;
    }

    private LinkedList<Messages> collectReadMessages(){
        LinkedList<Messages> readMessages = new LinkedList<>();
        LinkedList<Messages> messages = context.getUserMessages().allMessagesOfUser(user);
        for (Messages userMessages : messages) {
            if (userMessages.getIsSeen().get(user.getId())){
                User user1 = context.getUsers().get(userMessages.getUser2(user.getId()));
                if (!user1.getBlacklist().contains(user.getId()) &&
                        !user.getBlacklist().contains(user1.getId())) {
                    readMessages.add(userMessages);
                }
            }
        }
        return readMessages;
    }

    private LinkedList<Messages> sortListOfUserMessages(LinkedList<Messages> messages){
        for (int i = 0; i < messages.size(); i++) {
            for (int j = i+1; j < messages.size(); j++) {
                Messages messages1 = messages.get(i);
                Messages messages2 = messages.get(j);
                Message lastMessage1 = context.getMessages().get(messages1.getMessages().getLast());
                Message lastMessage2 = context.getMessages().get(messages2.getMessages().getLast());
                if (lastMessage2.getCreateAt().isAfter(lastMessage1.getCreateAt())){
                    messages.set(i, messages2);
                    messages.set(j, messages1);
                }
            }
        }
        return messages;
    }

    public LinkedList<Group> collectGroupsMessages(){
        LinkedList<Group> groups = new LinkedList<>();
        groups.addAll(sortListOfGroupMessages(collectUnreadGroupMessages()));
        groups.addAll(sortListOfGroupMessages(collectReadGroupMessages()));
        return groups;
    }

    private LinkedList<Group> collectUnreadGroupMessages(){
        LinkedList<Group> unreadGroupMessages = new LinkedList<>();
        LinkedList<Group> groups = context.getGroups().allGroupsOfUser(user);
        for (Group group : groups) {
            if (!group.getIsSeen().get(user.getId())) unreadGroupMessages.add(group);
        }
        return unreadGroupMessages;
    }

    private LinkedList<Group> collectReadGroupMessages(){
        LinkedList<Group> readGroupMessages = new LinkedList<>();
        LinkedList<Group> groups = context.getGroups().allGroupsOfUser(user);
        for (Group group : groups) {
            if (group.getIsSeen().get(user.getId())) readGroupMessages.add(group);
        }
        return readGroupMessages;
    }

    private LinkedList<Group> sortListOfGroupMessages(LinkedList<Group> groups){
        for (int i = 0; i < groups.size(); i++) {
            for (int j = i+1 ; j < groups.size() ; j++) {
                Group group1 = groups.get(i);
                Group group2 = groups.get(j);
                Message lastMessage1 = context.getMessages().get(group1.getMessages().getLast());
                Message lastMessage2 = context.getMessages().get(group2.getMessages().getLast());
                if (lastMessage2.getCreateAt().isAfter(lastMessage1.getCreateAt())){
                    groups.set(i, group2);
                    groups.set(j, group1);
                }
            }
        }
        return groups;
    }

    public Messages newUserMessages(User user){
        Messages messages = new Messages(this.user.getId(), user.getId());
        this.user.getMessages().add(messages.getId());
        user.getMessages().add(messages.getId());
        context.getUserMessages().add(messages);
        context.getUsers().update(this.user);
        context.getUsers().update(user);
        return messages;
    }

    public void sendUserMessage(Messages messages, String content, String imagePath){
        Message message = new Message(this.user.getId(), content);
        if (!imagePath.isEmpty()){
            message.setImagePath(imagePath);
        }
        messages.getMessages().add(message.getId());
        messages.getIsSeen().replace(messages.getUser2(user.getId()), false);
        context.getMessages().add(message);
        context.getUserMessages().update(messages);
    }

    public void sendGroupMessage(Group group, String content, String imagePath){
        Message message = new Message(this.user.getId(), content);
        if (!imagePath.isEmpty()){
            message.setImagePath(imagePath);
        }
        group.getMessages().add(message.getId());
        for (int userID : group.getIsSeen().keySet()) {
            if (userID != user.getId()) group.getIsSeen().replace(userID, false);
        }
        context.getMessages().add(message);
        context.getGroups().update(group);
    }

    public void deleteMessage(Message message){
//        LinkedList<Group> groups = context.getGroups().allGroupsOfUser(user);
//        for (Group group : groups) {
//            group.getMessages().remove(Integer.valueOf(message.getId()));
//            context.getGroups().update(group);
//        }
//        LinkedList<Messages> messages = context.getUserMessages().allMessagesOfUser(user);
//        for (Messages messages1 : messages){
//            messages1.getMessages().remove(Integer.valueOf(message.getId()));
//            context.getUserMessages().update(messages1);
//        }
//        context.getMessages().remove(message);

        message.setContent("Deleted message");
        message.setDeleted(true);
        context.getMessages().update(message);
    }

    public boolean canEdit(Message message){
        return !message.isForwarded() && !message.isDeleted();
    }

    public void editMessage(Message message, String newContent){
        message.setContent(newContent);
        context.getMessages().update(message);
    }

    public LinkedList<String> collectUsernames(){
        LinkedList<String> usernames = new LinkedList<>();
        LinkedList<User> followers = context.getUsers().followersOfUser(this.user);
        for (User user : followers) {
            if (!user.isInactive()){
                usernames.add(user.getUsername());
            }
        }
        return usernames;
    }

    public void forwardToUser(String username, Message message){
        Message message1 = new Message(user.getId(), message.getContent(), message.getForwardedFrom());
        Messages messages = context.getUserMessages().getMessagesWithUser(user, context.getUsers().get(username));
        if (messages == null) {
            messages = newUserMessages(context.getUsers().get(username));
        }
        messages.getMessages().add(message1.getId());
        messages.getIsSeen().replace(messages.getUser2(user.getId()), false);
        context.getMessages().add(message1);
        context.getUserMessages().update(messages);
    }

    public LinkedList<String> collectGroupsNames(){
        LinkedList<String> groupsNames = new LinkedList<>();
        LinkedList<Group> groups = context.getGroups().allGroupsOfUser(user);
        for (Group group : groups){
            groupsNames.add(group.getName());
        }
        return groupsNames;
    }

    public void forwardToGroup(String groupName, Message message){
        Message message1 = new Message(user.getId(), message.getContent(), message.getForwardedFrom());
        Group group = context.getGroups().get(user, groupName);
        group.getMessages().add(message1.getId());
        for (int userID : group.getIsSeen().keySet()) {
            if (userID != user.getId()) group.getIsSeen().replace(userID, false);
        }
        context.getMessages().add(message1);
        context.getGroups().update(group);
    }

    public LinkedList<String> collectUsernamesForAddingMembers(Group group){
        LinkedList<String> usernames = new LinkedList<>();
        LinkedList<User> followers = context.getUsers().followersOfUser(user);
        for (User user: followers){
            if (!group.getMembers().contains(user.getId()) &&
            !user.isInactive())
                usernames.add(user.getUsername());
        }
        return usernames;
    }

    public void addMember(Group group, String username){
        User user1 = context.getUsers().get(username);
        group.addMember(user1);
        user1.getGroups().add(group.getId());
        context.getUsers().update(user1);
        context.getGroups().update(group);
    }

    public void newGroup(String groupName){
        Group group = new Group(groupName);
        group.addMember(user);
        user.getGroups().add(group.getId());
        context.getGroups().add(group);
        context.getUsers().update(user);
    }
}
