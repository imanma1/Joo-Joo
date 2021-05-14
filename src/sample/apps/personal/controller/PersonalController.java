package sample.apps.personal.controller;

import sample.apps.authentication.controller.AuthController;
import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.authentication.model.User;
import sample.apps.explore.controller.ExploreController;
import sample.apps.personal.event.FurtherPersonalInformationEvent;
import sample.apps.personal.event.PersonalInformationEvent;
import sample.apps.post.model.Post;
import sample.apps.post.model.Tweet;
import sample.config.Config;
import sample.controller.Controller;

import java.io.File;
import java.time.LocalDate;
import java.util.LinkedList;

public class PersonalController extends Controller {

    public PersonalInformationEvent collectInformation(){
        String username = user.getUsername();
        String birth = getBirthDate();
        String bio = user.getBio();
        int followers = user.getFollowers().size();
        int following = user.getFollowing().size();
        int posts = user.getTweets().size();
        boolean hasImage = user.getImagePath() != null;
        File imageFile = new File(Config.getInstance().getImagesConfigPath() + "\\" + user.getImagePath());
        return new PersonalInformationEvent(
                this,
                username,
                birth,
                bio,
                followers,
                following,
                posts,
                hasImage,
                imageFile
        );
    }

    public FurtherPersonalInformationEvent collectFurtherInformation(){
        String username = user.getUsername();
        String name = user.getName();
        String email = user.getEmail();
        String bio = user.getBio();
        LocalDate birth = user.getBirth();
        String imagePath = user.getImagePath();
        return new FurtherPersonalInformationEvent(
                this,
                username,
                name,
                email,
                bio,
                birth,
                imagePath
        );
    }

    private String getBirthDate(){
        String birth = "";
        if (user.getBirth() != null){
            birth = user.getBirth().toString();
        }
        return birth;
    }

    public void tweet(String content, String imagePath){
        Tweet tweet = new Tweet(user.getId(), content);
        if (!imagePath.isEmpty()){
            tweet.setImagePath(imagePath);
        }
        user.getTweets().add(tweet.getId());
        context.getUsers().update(user);
        context.getTweets().add(tweet);
    }

    public void changeUsername(String username) throws UserAlreadyExistException {
        AuthController controller = new AuthController();
        if (controller.userExist(username)){
            user.setUsername(username);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    public void changeName(String name){
        user.setName(name);
        context.getUsers().update(user);
    }

    public void changeEmail(String email){
        user.setEmail(email);
        context.getUsers().update(user);
    }

    public void changeBio(String bio){
        user.setBio(bio);
        context.getUsers().update(user);
    }

    public void changeBirth(LocalDate birth){
        user.setBirth(birth);
        context.getUsers().update(user);
    }

    public void changeAvatar(String imagePath){
        user.setImagePath(imagePath);
        context.getUsers().update(user);
    }

    public LinkedList<User> collectFollowers(){
        return context.getUsers().followersOfUser(user);
    }

    public LinkedList<User> collectFollowing(){
        return context.getUsers().followingOfUser(user);
    }

    public LinkedList<Post> collectPosts(){
        LinkedList<Post> posts = context.getPosts().getPostsOfProfile(user);
        new ExploreController().deletePrivateAndMutedAndBlockedUsers(posts);
        return posts;
    }
}
