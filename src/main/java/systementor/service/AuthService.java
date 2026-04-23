package systementor.service;

import org.mindrot.jbcrypt.BCrypt;
import systementor.model.User;
import systementor.repository.UserRepository;

public class AuthService {

    private final UserRepository repository = new UserRepository();



    public User login(String username, String password) {
        if (username == null || username.isBlank()) {
            System.out.println("Username is empty");
            return null;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password is empty");
            return null;
        }
        User user = repository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        boolean checkPw = BCrypt.checkpw(password,user.getPassword());
        if(checkPw){
            return user;
        }
        return null;
    }


    public boolean register(String username, String password) {

        if (username == null || username.isBlank()) {
            System.out.println("Username is empty");
            return false;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password is empty");
            return false;
        }
        if(repository.existsByUsername(username)){
            System.out.println("Username already exists");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return repository.saveUser(username,hashedPassword, "USER");
    }

}
