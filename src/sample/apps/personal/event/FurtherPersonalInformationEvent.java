package sample.apps.personal.event;

import java.time.LocalDate;
import java.util.EventObject;

public class FurtherPersonalInformationEvent extends EventObject {
    private final String username;
    private final String name;
    private final String email;
    private final String bio;
    private final LocalDate birth;
    private final String imagePath;

    public FurtherPersonalInformationEvent(Object source, String username, String name, String email, String bio, LocalDate birth, String imagePath) {
        super(source);
        this.username = username;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.birth = birth;
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getImagePath() {
        return imagePath;
    }
}
