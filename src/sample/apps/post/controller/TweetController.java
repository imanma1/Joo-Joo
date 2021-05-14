package sample.apps.post.controller;

import sample.apps.authentication.model.User;
import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;
import sample.apps.messages.model.Message;
import sample.apps.messages.model.Messages;
import sample.apps.post.event.PostInformationEvent;
import sample.apps.post.exception.AccountIsPrivateException;
import sample.apps.post.model.Comment;
import sample.apps.post.model.Like;
import sample.apps.post.model.Post;
import sample.config.Config;
import sample.controller.Controller;

import java.io.File;
import java.util.LinkedList;

public class TweetController extends Controller {

    public PostInformationEvent collectInformation(Post post){
        User user = context.getUsers().get(post.getSender());
        String tweet = post.getContent();
        String username = user.getUsername();
        String date = post.getCreateAt().getMonth() + "/" +
                post.getCreateAt().getDayOfMonth() + "/" +
                post.getCreateAt().getYear() + " ";
        int likes = post.getLikes().size();
        int retweets = post.getRetweets().size();
        int replies = post.getComments().size();
        boolean hasImage = post.isHasImage();
        File imageFile = new File(Config.getInstance().getImagesConfigPath() + "\\" + post.getImagePath());
        boolean hasProfileImage = user.getImagePath() != null;
        File profileImageFile = new File(Config.getInstance().getImagesConfigPath() + "\\" + user.getImagePath());
        return new PostInformationEvent(this, tweet, username, date, likes, retweets, replies, hasImage, imageFile, hasProfileImage, profileImageFile);
    }

    public boolean checkIfUserLikedPost(Post post){
        LinkedList<Like> likes = context.getLikes().likesOfPost(post);
        for (Like like : likes) {
            if (like.getLiker() == user.getId()) return true;
        }
        return false;
    }

    public boolean checkIfUserRetweetedPost(Post post){
        for (int userID : post.getRetweets()) {
            if (userID == user.getId()) return true;
        }
        return false;
    }

    public boolean checkIfUserReportedPost(Post post){
        for (int userID : post.getReports()) {
            if (userID == user.getId()) return true;
        }
        return false;
    }

    public LinkedList<Comment> collectReplies(Post post){
        LinkedList<Comment> comments = context.getPosts().getComments().commentsOfPost(post);
        LinkedList<Comment> replies = new LinkedList<>();
        for (Comment comment : comments) {
            if (!user.getBlacklist().contains(comment.getSender()) &&
            !user.getMuted().contains(comment.getSender())){
                replies.add(comment);
            }
        }
        sortReplies(replies);
        return replies;
    }

    private void sortReplies(LinkedList<Comment> comments){
        for (int i = 0; i < comments.size(); i++) {
            for (int j = i+1; j < comments.size(); j++) {
                Comment comment = comments.get(i);
                Comment comment1 = comments.get(j);
                if (comment1.getCreateAt().isAfter(comment.getCreateAt())) {
                    comments.set(i, comment1);
                    comments.set(j, comment);
                }
            }
        }
    }

    public void like(Post post){
        Like like = new Like(post.getId(), user.getId());
        post.getLikes().add(like.getId());
        context.getLikes().add(like);
        context.getPosts().update(post);
    }

    public void dislike(Post post){
        LinkedList<Like> likes = context.getLikes().likesOfPost(post);
        for (Like like : likes) {
            if (like.getLiker() == user.getId()){
                post.getLikes().remove(Integer.valueOf(post.getId()));
                context.getLikes().remove(like);
                context.getPosts().update(post);
            }
        }
    }

    public void retweet(Post post) throws AccountIsPrivateException {
        if (!context.getUsers().get(post.getSender()).isPrivate()){
            user.getTweets().add(post.getId());
            post.getRetweets().add(user.getId());
            context.getPosts().update(post);
            context.getUsers().update(user);
        } else {
            throw new AccountIsPrivateException();
        }
    }

    public void undoRetweet(Post post){
        user.getTweets().remove(Integer.valueOf(post.getId()));
        post.getRetweets().remove(Integer.valueOf(user.getId()));
        context.getUsers().update(user);
        context.getPosts().update(post);
    }

    public void report(Post post){
        post.getReports().add(user.getId());
        context.getPosts().update(post);
        checkForReports(post);
    }

    private void checkForReports(Post post){
        if (post.getReports().size() >= 3){
            post.setReported(true);
            context.getPosts().update(post);
        }
    }

    public void reply(Post post, String reply, String imagePath){
        Comment comment = new Comment(user.getId(), reply, post.getId());
        if (!imagePath.isEmpty()){
            comment.setImagePath(imagePath);
        }
        user.getComments().add(comment.getId());
        post.getComments().add(comment.getId());
        context.getPosts().getComments().add(comment);
        context.getPosts().update(post);
        context.getUsers().update(user);
    }

    public void shareForUser(Post post, String username){
        String content = "@" + context.getUsers().get(post.getSender()) + " posted:\n" + post.getContent();
        Message message1 = new Message(user.getId(), content, user.getId());
        Messages messages = context.getUserMessages().getMessagesWithUser(user, context.getUsers().get(username));
        if (messages == null) {
            messages = new MessagesController().newUserMessages(context.getUsers().get(username));
        }
        messages.getMessages().add(message1.getId());
        messages.getIsSeen().replace(messages.getUser2(user.getId()), false);
        context.getMessages().add(message1);
        context.getUserMessages().update(messages);
    }

    public void shareForGroup(Post post, String groupName){
        String content = "@" + context.getUsers().get(post.getSender()) + " posted:\n" + post.getContent();
        Message message1 = new Message(user.getId(), content, user.getId());
        Group group = context.getGroups().get(user, groupName);
        group.getMessages().add(message1.getId());
        for (int userID : group.getIsSeen().keySet()) {
            if (userID != user.getId()) group.getIsSeen().replace(userID, false);
        }
        context.getMessages().add(message1);
        context.getGroups().update(group);
    }
}
