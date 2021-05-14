package sample.config;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DBConfig {
    private static DBConfig instance;

    public static DBConfig getInstance() throws IOException {
        if (instance == null){
            instance = new DBConfig();
        }
        return instance;
    }

    private final String DB_CONFIG_PATH;

    private String lastID;
    private String DBUsersPath;
    private String DBTweetsPath;
    private String DBCommentsPath;
    private String DBLikesPath;
    private String DBMessagesPath;
    private String DBGroupsPath;
    private String DBRequestPath;
    private String DBUserMessagesPath;

    public DBConfig() throws IOException {
        DB_CONFIG_PATH = Config.getInstance().getDBPath();
        setProperties();
    }

    private void setProperties()  {
        lastID = DB_CONFIG_PATH + "\\lastID.txt";
        DBUsersPath = DB_CONFIG_PATH + "\\users";
        DBTweetsPath = DB_CONFIG_PATH + "\\tweets";
        DBCommentsPath = DB_CONFIG_PATH + "\\comments";
        DBLikesPath = DB_CONFIG_PATH + "\\likes";
        DBMessagesPath = DB_CONFIG_PATH + "\\messages";
        DBGroupsPath = DB_CONFIG_PATH + "\\groups";
        DBRequestPath = DB_CONFIG_PATH + "\\requests";
        DBUserMessagesPath = DB_CONFIG_PATH + "\\user_messages";
    }

    public String getLastID() {
        return lastID;
    }

    public String getDBUsersPath() {
        return DBUsersPath;
    }

    public String getDBTweetsPath() {
        return DBTweetsPath;
    }

    public String getDBCommentsPath() {
        return DBCommentsPath;
    }

    public String getDBLikesPath() {
        return DBLikesPath;
    }

    public String getDBMessagesPath() {
        return DBMessagesPath;
    }

    public String getDBGroupsPath() {
        return DBGroupsPath;
    }

    public String getDBRequestPath() {
        return DBRequestPath;
    }

    public String getDBUserMessagesPath() {
        return DBUserMessagesPath;
    }

}
