package DomainClasses.Entites.User;

import java.time.LocalDateTime;

public abstract class Users {
    public Users() {

    }

    public Users(Users users) {
        this.id = users.id;
        this.username = users.username;
        this.regTime = users.regTime;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private LocalDateTime regTime;

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }
}
