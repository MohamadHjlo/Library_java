package ServiceLayer.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DomainClasses.Entites.Book.Book;
import DomainClasses.Entites.Book.Enum.BookSearchType;
import DomainClasses.Entites.User.Admin;
import DomainClasses.Entites.User.User;
import DomainClasses.Entites.User.Users;
import DomainClasses.Entites.User.Enum.AdminType;
import ServiceLayer.Interfaces.ILibrary;
import Utilities.FileUtill.ColorizeWriter.*;

public class MyLib implements ILibrary {

    public MyLib() {
        if (books == null) {
            books = new Book();
        }
    }

    private static Book books;

    public static Book getBooks() {
        return books;
    }

    public static void setBooks(Book books) {
        MyLib.books = books;
    }

    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        MyLib.users = users;
    }

    public static ArrayList<Admin> admins = new ArrayList<>();

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static void setAdmins(ArrayList<Admin> admins) {
        MyLib.admins = admins;
    }

    private static ArrayList<Users> allUsers = new ArrayList<>();

    public static ArrayList<Users> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<Users> allUsers) {
        MyLib.allUsers = allUsers;
    }

    private static int currentUserId;

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int currentUserId) {
        MyLib.currentUserId = currentUserId;
    }

    private static Boolean isCurrentUserIsAdmin = false;

    public static Boolean getIsCurrentUserIsAdmin() {
        return isCurrentUserIsAdmin;
    }

    public static void setIsCurrentUserIsAdmin(Boolean isCurrentUserIsAdmin) {
        MyLib.isCurrentUserIsAdmin = isCurrentUserIsAdmin;
    }

    private static AdminType currentUserAdminType;

    public static AdminType getCurrentUserAdminType() {
        return currentUserAdminType;
    }

    public static void setCurrentUserAdminType(AdminType currentUserAdminType) {
        MyLib.currentUserAdminType = currentUserAdminType;
    }

    private static Boolean isCurrentUserIsLoggedIn = false;

    public static Boolean getIsCurrentUserIsLoggedIn() {
        return isCurrentUserIsLoggedIn;
    }

    public static void setIsCurrentUserIsLoggedIn(Boolean isCurrentUserIsLoggedIn) {
        MyLib.isCurrentUserIsLoggedIn = isCurrentUserIsLoggedIn;
    }

    public int resCount;

    public void seedDefaultData() {
        for (int i = 0; i < 10; i++) {
            HashSet<HashMap<String, Object>> booksSet = new HashSet<HashMap<String, Object>>();
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("Name", "BookName" + i);
            book.put("AgeGroup", "AgeGroup" + i);
            book.put("Abstract", "BookAbstract" + i);
            book.put("Author", "BookAuthor" + i);
            book.put("ISBN", String.valueOf(i * 123));
            book.put("Status", true);
            booksSet.add(book);
            books.register(booksSet);

        }
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUsername("User" + i);
            user.setRegTime(LocalDateTime.now());
            user.setAge(i + 15);

            users.add(user);
        }
        for (int i = 10; i < 20; i++) {
            Admin admin = new Admin();
            admin.setId(i + 1);
            admin.setUsername("Admin" + i);
            admin.setRegTime(LocalDateTime.now());
            admin.setType((i % 2 == 0 ? AdminType.Normal : i == 9 ? AdminType.SuperAdmin : AdminType.Owner));
            admin.setPassword("1234" + i);
            admin.setActive((i % 2 == 0 ? true : false));
            admins.add(admin);
        }
        allUsers.addAll(users);
        allUsers.addAll(admins);
    }

    public void menu() {

        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nplease choose a option :\n");
            System.out.println("1 : Search book");
            System.out.println("2 : Register new book");
            System.out.println("3 : Register new user");
            System.out.println("4 : Add integrity");
            System.out.println("5 : Return back integrity");
            System.out.println("6 : List Of Books");
            System.out.println("7 : List Of Users");
            System.out.println("8 : Exit");

            System.out.println("\nEnter your option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter search type \n1:ISBN, \n2: \nName, \n3:Author, \n4: Abstract ");
                    int searchType = scanner.nextInt();

                    scanner.nextLine();
                    System.out.println("Enter keyword for search: ");
                    String keyword = scanner.nextLine();
                    HashSet<HashMap<String, Object>> searchBook = new HashSet<HashMap<String, Object>>();
                    switch (searchType) {
                        case 1:
                            searchBook = searchBook(BookSearchType.ISBN, keyword);
                            break;
                        case 2:
                            searchBook = searchBook(BookSearchType.Name, keyword);
                            break;
                        case 3:
                            searchBook = searchBook(BookSearchType.Author, keyword);
                            break;
                        case 4:
                            searchBook = searchBook(BookSearchType.Abstract, keyword);
                            break;
                        default:
                            break;
                    }

                    if (searchBook != null) {
                        System.out.println("Book found!");
                        for (HashMap<String, Object> book : searchBook) {
                            for (String key : book.keySet()) {
                                System.out.println(" " + key + " : " + book.get(key));
                            }
                            System.out.println(" ******  ");
                        }

                    } else {
                        Colorizer.ColorizeWriter.printlnRed("Book not found.");
                    }
                    break;
                case 2:
                    if (isCurrentUserIsAdmin) {
                        HashSet<HashMap<String, Object>> booksSet = new HashSet<HashMap<String, Object>>();
                        HashMap<String, Object> book = new HashMap<String, Object>();
                        System.out.println("Enter book ISBN: ");
                        scanner.nextLine();
                        String newIsbn = scanner.nextLine();
                        System.out.println("Enter book name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter book ageGroup: ");
                        String ageGroup = scanner.nextLine();
                        System.out.println("Enter book Abstract: ");
                        String Abstract = scanner.nextLine();
                        System.out.println("Enter book author: ");
                        String author = scanner.nextLine();
                        book.put("Name", name);
                        book.put("AgeGroup", ageGroup);
                        book.put("Abstract", Abstract);
                        book.put("Author", author);
                        book.put("ISBN", newIsbn);
                        book.put("Status", true);
                        booksSet.add(book);
                        books.register(booksSet);
                        System.out.println("Book Registered successfully");
                    } else {
                        Colorizer.ColorizeWriter.printlnRed("you cant add new book, because you are not admin");
                    }
                    break;
                case 3:
                    if (isCurrentUserIsAdmin) {
                        User user = new User();
                        System.out.println("Enter user ID: ");
                        int id = scanner.nextInt();
                        System.out.println("Enter user username: ");
                        String userName = scanner.next();
                        System.out.println("Enter user Age: ");
                        int age = scanner.nextInt();
                        user.setId(id);
                        user.setUsername(userName);
                        user.setRegTime(LocalDateTime.now());
                        user.setAge(age);
                        users.add(user);
                        System.out.println("User Registered successfully");
                    } else {
                        Colorizer.ColorizeWriter.printlnRed("you cant add new user, because you are not admin");
                    }

                    break;
                case 4:
                    System.out.println("Enter book ISBN: ");
                    String bookIsbn = scanner.next();
                    System.out.println("Enter user Id: ");
                    int userId = scanner.nextInt();
                    reserveBook(bookIsbn, userId);
                    break;
                case 5:
                    System.out.println("Enter book ISBN: ");
                    String bookIsbnForReturn = scanner.next();
                    System.out.println("Enter user Id: ");
                    int returnUserId = scanner.nextInt();
                    returnBook(bookIsbnForReturn, returnUserId);

                    break;
                case 6:
                    showAllBooks();
                    break;
                case 7:

                    showAllUsers();

                    break;
                case 8:
                    break;
                default:
                    Colorizer.ColorizeWriter.printlnRed("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (option != 8);
        currentUserId = 0;
        isCurrentUserIsAdmin = false;
        Colorizer.ColorizeWriter.printLnYellow("You Logged Out Successfully !");

    }

    public HashSet<HashMap<String, Object>> searchBook(BookSearchType searchType, String keyword) {
        ArrayList<HashSet<HashMap<String, Object>>> bookList = books.getBookList();

        switch (searchType) {
            case Name:
                for (HashSet<HashMap<String, Object>> set : bookList) {
                    for (HashMap<String, Object> map : set) {
                        if (map.containsKey(BookSearchType.Name.toString())
                                && map.get(BookSearchType.Name.toString()).equals(keyword)) {
                            return set;
                            // filteredList.add(book);
                        }
                    }
                }
                break;
            case Author:
                for (HashSet<HashMap<String, Object>> set : bookList) {
                    for (HashMap<String, Object> book : set) {
                        if (book.containsKey(BookSearchType.Author.toString())
                                && book.get(BookSearchType.Author.toString()).equals(keyword)) {
                            return set;

                        }
                    }
                }

                break;
            case ISBN:
                for (HashSet<HashMap<String, Object>> set : bookList) {
                    for (HashMap<String, Object> book : set) {
                        if (book.get(BookSearchType.ISBN.toString()).equals(keyword)) {
                            return set;

                        }
                    }
                }
                break;
            case Abstract:
                Pattern pattern = Pattern.compile(keyword);
                for (HashSet<HashMap<String, Object>> set : bookList) {
                    for (HashMap<String, Object> book : set) {
                        if (book.containsKey(BookSearchType.Abstract.toString())) {
                            Matcher matcher = pattern
                                    .matcher((CharSequence) book.get(BookSearchType.Abstract.toString()));
                            if (matcher.find()) {
                                return set;
                            }

                        }
                    }
                }

                break;
            default:
                break;
        }
        Colorizer.ColorizeWriter.printlnRed("Book not found with this search key : " + keyword);
        return null;
    }

    public boolean login(String userName, String password) {
        boolean findFlag = false;
        for (Admin user : admins) {

            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                Colorizer.ColorizeWriter.printlnGreen("Welcome Admin ! You now logged in!");
                findFlag = true;
                isCurrentUserIsAdmin = true;
                currentUserAdminType = user.getType();
                isCurrentUserIsLoggedIn = true;
                currentUserId = user.getId();
            }
        }
        if (!findFlag) {
            for (User user : users) {

                if (user.getUsername().equals(userName)) {
                    Colorizer.ColorizeWriter.printlnGreen("Welcome User ! You now logged in!");
                    findFlag = true;
                    isCurrentUserIsAdmin = false;
                    isCurrentUserIsAdmin = false;
                    isCurrentUserIsLoggedIn = true;
                }
            }
        }
        if (!findFlag) {

            Colorizer.ColorizeWriter.printlnRed("Oh! this user dosnt exists! please try again ");

        }
        return findFlag;

    }

    public void reserveBook(String isbn, int userId) {

        HashSet<HashMap<String, Object>> searchResult = searchBook(BookSearchType.ISBN, isbn);
        if (searchResult != null) {
            User userFinded = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
            if (userFinded != null) {
                if (books.ageOK(userFinded.getAge())) {
                    for (HashMap<String, Object> map : searchResult) {
                        boolean bookStatus = (boolean) map.get("Status");
                        if (bookStatus) {

                            if (userFinded.getReserved() != null) {
                                if (userFinded.getReserved().equals(isbn)) {
                                    System.out.println("this user currently has this book !");
                                    return;
                                }

                            }
                            books.getReserved().add(userFinded);
                            books.setResCount(books.getResCount() + 1);
                            map.put("Status", false);
                            userFinded.setReserved(isbn);
                            books.writeStatus(searchResult,userFinded,books);
                            System.out.println("Book Reserved successfully");
                        } else {
                            System.out.println("this book status is false please return it first then reserve it ");
                        }
                    }
                }
            } else {
                System.out.println("your user age is not ok");
            }
        }

    }

    public void returnBook(String isbn, int userId) {
        HashSet<HashMap<String, Object>> searchResult = searchBook(BookSearchType.ISBN, isbn);
        if (searchResult != null) {
            User userFinded = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
            if (userFinded != null) {
                if (books.ageOK(userFinded.getAge())) {
                    for (HashMap<String, Object> map : searchResult) {
                        if (userFinded.getReserved() == null) {
                            System.out.println("this user has not any book !");
                            return;
                        }
                        books.getReserved().remove(userFinded);
                        books.setResCount(books.getResCount() - 1);
                        map.put("Status", true);
                        userFinded.setReserved(null);
                        books.writeStatus(searchResult,userFinded,books);
                        Colorizer.ColorizeWriter.printLnYellow("Book returnd successfully");

                    }
                } else {
                    System.out.println("your user age is not ok");
                }

            }

        }
    }

    public void showAllUsers() {
        ArrayList<User> users = getUsers();
        for (User user : users) {

            System.out.println("Id :" + user.getId());
            System.out.println("Username :" + user.getUsername());
            System.out.println("Age :" + user.getAge());
            System.out.println("Reserved :" + user.getReserved());
            System.out.println("RegTime :" + user.getRegTime());
            Colorizer.ColorizeWriter.printlnBlue(" ************* ");

        }

    }

    public void showAllBooks() {
        ArrayList<HashSet<HashMap<String, Object>>> bookList = books.getBookList();
        for (HashSet<HashMap<String, Object>> set : bookList) {
            for (HashMap<String, Object> map : set) {
                for (String key : map.keySet()) {
                    System.out.println(" " + key + " : " + map.get(key));
                }
            }
            Colorizer.ColorizeWriter.printlnBlue(" ************* ");

        }

    }
}
