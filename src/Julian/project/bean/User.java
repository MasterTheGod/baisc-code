package Julian.project.bean;

import java.util.Random;

public class User {
    private String id;
    private String username;
    private String password;
    private boolean state;


    public String getId() {
        return id;
    }

    public String setId() {
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("Julian");
        for (int i = 0; i < 5; i++) {
            sb.append(rd.nextInt(10));
        }
        return sb.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User() {
        id = setId();
        state = true;
    }

    public User(String username, String password) {
        this.id = setId();
        this.username = username;
        this.password = password;
        this.state = true;
    }
}
