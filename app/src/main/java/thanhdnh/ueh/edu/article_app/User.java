package thanhdnh.ueh.edu.article_app;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String uname;
    private String password;
    private String urlProfile;
    private String shortBio;
    private String gender; // "Nam" hoặc "Nữ"
    private int age;       // Tuổi

    public User(int id, String uname, String password, String urlProfile, String shortBio, String gender, int age) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.urlProfile = urlProfile;
        this.shortBio = shortBio;
        this.gender = gender;
        this.age = age;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getUname() { return uname; }
    public String getPassword() { return password; }
    public String getUrlProfile() { return urlProfile; }
    public String getShortBio() { return shortBio; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
}