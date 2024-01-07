package ServiceLayer.Interfaces;

import java.util.HashMap;
import java.util.HashSet;

import DomainClasses.Entites.Book.Enum.BookSearchType;

public interface ILibrary {

    public void menu();

    public HashSet<HashMap<String, Object>> searchBook(BookSearchType searchType, String keyword);

    public boolean login(String userName, String password);

    public void reserveBook(String isbn, int userId);

    public void returnBook(String isbn, int userId);

}
