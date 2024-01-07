package Application;
import java.util.Scanner;
import ServiceLayer.Services.MyLib;

public class App {

    public static void main(String[] args) {

        // valid admin :
        // user : Admin10
        // pass : 123410
        MyLib myLib = new MyLib();
        myLib.seedDefaultData();
        Scanner sc = new Scanner(System.in);
        myLib.showAllUsers();
        while (true) {
            System.out.println("Enter user username: ");
            String userName = sc.nextLine();
            System.out.println("Enter password: ");
            String password = sc.nextLine();
            boolean correctLogin = myLib.login(userName, password);
            if (correctLogin) {
                myLib.menu();
            }
        }

    }
}
