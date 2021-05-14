package sample.db;

public class Context {
    private final UserDB users;
    private final TweetDB tweets;
    private final PostDB posts;
    private final LikeDB likes;
    private final RequestDB requests;
    private final MessageDB messages;
    private final MessagesDB userMessages;
    private final GroupDB groups;

    public Context() {
        users = new UserDB();
        tweets = new TweetDB();
        posts = new PostDB();
        likes = new LikeDB();
        requests = new RequestDB();
        messages = new MessageDB();
        userMessages = new MessagesDB();
        groups = new GroupDB();
    }

    public UserDB getUsers() {
        return users;
    }

    public TweetDB getTweets() {
        return tweets;
    }

    public PostDB getPosts() {
        return posts;
    }

    public LikeDB getLikes() {
        return likes;
    }

    public RequestDB getRequests() {
        return requests;
    }

    public MessageDB getMessages() {
        return messages;
    }

    public MessagesDB getUserMessages() {
        return userMessages;
    }

    public GroupDB getGroups() {
        return groups;
    }
}
