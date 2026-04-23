package systementor.ui;

import systementor.model.User;
import systementor.service.AuthService;

import java.util.Scanner;

public class ConsoleMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final AuthService service = new AuthService();

    public void start(){
        boolean running = true;


        while(running) {
            System.out.println("----Secure Note----");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> running = false;
                default -> System.out.println("Invalid choice");
            }


        }



    }

    private void login() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.println("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = service.login(username,password);
        if(user != null) {
            System.out.println("Login successful for user: " + user.getUsername());
        } else {
            System.out.println("Login failed");
        }

    }

    private void register(){
        System.out.println("Enter your username:");
        String username = scanner.nextLine().trim();

        System.out.println("Enter your password:");
        String password = scanner.nextLine().trim();

        boolean success = service.register(username,password);

        if(success){
            System.out.println("User registered successfully");
        } else {
            System.out.println("Could not register user");
        }


    }



}
