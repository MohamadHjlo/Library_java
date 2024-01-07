package DomainClasses.Entites.Book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import DomainClasses.Entites.User.User;
import DomainClasses.Entites.User.Users;
import Utilities.FileUtill.Logging.*;
import javafx.concurrent.Task;

public class Book {

  private static ArrayList<HashSet<HashMap<String, Object>>> bookList = new ArrayList<>();

  private static ArrayList<Users> reserved = new ArrayList<>();

  private int resCount;

  public ArrayList<HashSet<HashMap<String, Object>>> getBookList() {
    return bookList;
  }

  public void setBookList(ArrayList<HashSet<HashMap<String, Object>>> bookList) {
    Book.bookList = bookList;
  }

  public ArrayList<Users> getReserved() {
    return reserved;
  }

  public void setReserved(ArrayList<Users> reserved) {
    this.reserved = reserved;
  }

  public int getResCount() {
    return resCount;
  }

  public void setResCount(int resCount) {
    this.resCount = resCount;
  }

  public void register(HashSet<HashMap<String, Object>> newBook) {
    bookList.add(newBook);
  }

  public Task writeStatus(HashSet<HashMap<String, Object>> newBook, User user, Book book) {
    String fileName = "book_log.txt";

    StringBuilder stb = new StringBuilder();
    stb.append("Book Changed with This Status at : ");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    stb.append(LocalDateTime.now().format(formatter) + "\n");
    stb.append(newBook);
    stb.append("\nWith this User: [{");
    stb.append("Id :" + user.getId());
    stb.append(" Username :" + user.getUsername());
    stb.append(" Age :" + user.getAge());
    stb.append(" Reserved :" + user.getReserved());
    stb.append(" RegTime :" + user.getRegTime() + "}]");
    stb.append("\nWith this ResCount: ");
    stb.append(book.getResCount());

    Logging.setLogsToFile(fileName, stb.toString());

    return null;
  }

  public String lastStatus(String fileName) {
    return Logging.readLogFile(fileName);
  }

  public boolean ageOK(int age) {
    if (age >= 18) {
      return true;
    } else {
      return false;
    }
  }

}
