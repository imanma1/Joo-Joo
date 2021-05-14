package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class UserDB implements DBSet<User> {
    private File DBDirectory;

    public UserDB() {
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBUsersPath());
        } catch (IOException ignored){ }
    }

    @Override
    public User get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            User user = gson.fromJson(bufferedReader, User.class);
            bufferedReader.close();
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User get(String username){
        try {
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                User user = gson.fromJson(bufferedReader, User.class);
                bufferedReader.close();
                if (user.getUsername().equals(username)) return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<User> all() {
        try {
            LinkedList<User> users = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                User user = gson.fromJson(bufferedReader, User.class);
                users.add(user);
                bufferedReader.close();
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<User> followingOfUser(User user){
        LinkedList<User> users = new LinkedList<>();
        for (int userID : user.getFollowing()) {
            users.add(get(userID));
        }
        return users;
    }

    public LinkedList<User> followersOfUser(User user){
        LinkedList<User> users = new LinkedList<>();
        for (int userID : user.getFollowers()) {
            users.add(get(userID));
        }
        return users;
    }

    @Override
    public void add(User user) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, user.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(user);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        File file = new File(DBDirectory, user.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(User user) {
        remove(user);
        add(user);
    }
}
