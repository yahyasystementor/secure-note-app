package systementor.service;

import org.mindrot.jbcrypt.BCrypt;
import systementor.repository.UserRepository;

public class AuthService {

    private final UserRepository repository = new UserRepository();


    public boolean register(String username, String password) {
        if (username == null || username.isBlank()) {
            System.out.println("Username is empty");
            return false;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password is empty");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
        return repository.saveUser(username,hashedPassword, "USER");

    }

}
