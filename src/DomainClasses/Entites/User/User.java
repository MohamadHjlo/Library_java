package DomainClasses.Entites.User;

import java.util.HashMap;

import DomainClasses.Entites.Book.Book;

public class User extends Users {

    public User() {
       
    }
    public User(User user) {
        super(user);
        this.age = user.age;
        this.reserved = user.reserved;
    }

    private int age;

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    private String reserved;

    public String getReserved() {
        return reserved;
    }
    public void setReserved(String reserved) {
        this.reserved = reserved;
    }
    public void isReserved(String isbn) {

    }
}
