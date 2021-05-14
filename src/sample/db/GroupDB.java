package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.messages.model.Group;
import sample.apps.post.model.Like;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class GroupDB implements DBSet<Group> {
    private File DBDirectory;

    public GroupDB(){
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBGroupsPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Group get(int id){
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Group group = gson.fromJson(bufferedReader, Group.class);
            bufferedReader.close();
            return group;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Group get(User user, String groupName){
        LinkedList<Group> groups = allGroupsOfUser(user);
        for (Group group : groups) {
            if (group.getName().equals(groupName)) return group;
        }
        return null;
    }

    @Override
    public LinkedList<Group> all() {
        try {
            LinkedList<Group> groups = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Group group = gson.fromJson(bufferedReader, Group.class);
                groups.add(group);
                bufferedReader.close();
            }
            return groups;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Group> allGroupsOfUser(User user){
        LinkedList<Group> groups = new LinkedList<>();
        for (int groupID : user.getGroups()) {
            groups.add(get(groupID));
        }
        return groups;
    }

    @Override
    public void add(Group group) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, group.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(group);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Group group) {
        File file = new File(DBDirectory, group.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Group group) {
        remove(group);
        add(group);
    }
}
