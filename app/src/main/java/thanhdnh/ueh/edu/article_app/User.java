package thanhdnh.ueh.edu.article_app;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String uname;
    private String password;
    private String urlProfile;
    private String shortBio;

    public User(int id, String uname, String password, String urlProfile, String shortBio) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.urlProfile = urlProfile;
        this.shortBio = shortBio;
    }

    public int getId() { return id; }
    public String getUname() { return uname; }
    public String getPassword() { return password; }
    public String getUrlProfile() { return urlProfile; }
    public String getShortBio() { return shortBio; }
}