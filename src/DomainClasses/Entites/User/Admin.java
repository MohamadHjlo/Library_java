package DomainClasses.Entites.User;

import java.time.LocalDateTime;

import DomainClasses.Entites.User.Enum.AdminType;

public class Admin extends Users {

    public Admin() {

    }

    public Admin(Admin admin) {
        super(admin);
        this.password = admin.password;
        this.active = admin.active;
        this.type = admin.type;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private AdminType type;

    public AdminType getType() {
        return type;
    }

    public void setType(AdminType type) {
        this.type = type;
    }

    public void login(Admin admin) {
        this.password = admin.password;
        this.setPassword(admin.getPassword());
        this.setUsername(admin.getUsername());

    }

}
