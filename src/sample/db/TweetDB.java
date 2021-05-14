package sample.db;

import com.google.gson.Gson;
import sample.apps.authentication.model.User;
import sample.apps.post.model.Tweet;
import sample.config.DBConfig;

import java.io.*;
import java.util.LinkedList;

public class TweetDB implements DBSet<Tweet> {
    private File DBDirectory;

    public TweetDB() {
        try {
            DBDirectory = new File(DBConfig.getInstance().getDBTweetsPath());
        } catch (IOException ignored){ }
    }

    @Override
    public Tweet get(int id) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, id + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Tweet tweet = gson.fromJson(bufferedReader, Tweet.class);
            bufferedReader.close();
            return tweet;
        } catch (IOException e) {
//            e.printStackTrace(); ///file not found
        }
        return null;
    }

    @Override
    public LinkedList<Tweet> all() {
        try {
            LinkedList<Tweet> tweets = new LinkedList<>();
            Gson gson = new Gson();
            for (File file : DBDirectory.listFiles()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                Tweet tweet = gson.fromJson(bufferedReader, Tweet.class);
                tweets.add(tweet);
                bufferedReader.close();
            }
            return tweets;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<Tweet> allTweetsOfUser(User user){
        LinkedList<Tweet> tweets = new LinkedList<>();
        LinkedList<Tweet> allTweets = all();
        for (Tweet tweet : allTweets) {
            if (tweet.getSender() == user.getId())
                tweets.add(tweet);
        }
        return tweets;
    }

    @Override
    public void add(Tweet tweet) {
        try {
            Gson gson = new Gson();
            File file = new File(DBDirectory, tweet.getId() + ".txt");
            file.createNewFile();
            String data = gson.toJson(tweet);
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            printStream.print(data);
            printStream.flush();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tweet tweet) {
        File file = new File(DBDirectory, tweet.getId() + ".txt");
        file.delete();
    }

    @Override
    public void update(Tweet tweet) {
        remove(tweet);
        add(tweet);
    }
}
