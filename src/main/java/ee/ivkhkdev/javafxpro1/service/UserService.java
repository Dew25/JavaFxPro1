package ee.ivkhkdev.javafxpro1.service;

import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.model.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public static enum ROLES{USER, MANAGER, ADMINISTRATOR};
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(13));
    }

    public AppUser save(String firstname, String lastname,String login, String password) {
        AppUser user = new AppUser();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLogin(login);
        user.setPassword(this.hashPassword(password));
        user.getRoles().add(ROLES.USER.toString());
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean authenticate(String login, String password) {
        AppUser user = userRepository.findByLogin(login);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return BCrypt.checkpw(password,user.getPassword());
    }

    public void setSuperUser() {
        if(userRepository.count() == 0) {
            AppUser user = new AppUser();
            user.setFirstname("Ivan");
            user.setLastname("Ivanov");
            user.setLogin("admin");
            String hashedPassword = BCrypt.hashpw("12345", BCrypt.gensalt(12));
            user.setPassword(hashedPassword);
            user.getRoles().add(ROLES.USER.toString());
            user.getRoles().add(ROLES.MANAGER.toString());
            user.getRoles().add(ROLES.ADMINISTRATOR.toString());
            userRepository.save(user);
        }
    }
}
