package ee.ivkhkdev.javafxpro1.service;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.model.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
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
        user.setPassword(hashPassword(password));
        user.getRoles().add(JavaFxPro1Application.ROLE.USER.toString());
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean authenticate(String login, String password) {
        AppUser user = userRepository.findByLogin(login);
        if(BCrypt.checkpw(password, user.getPassword())){
            JavaFxPro1Application.currentUser = user;
            return true;
        }else{
            return false;
        }
    }

    public void setSuperUser() {
        if(userRepository.count() == 0) {
            AppUser user = new AppUser();
            user.setFirstname("Ivan");
            user.setLastname("Ivanov");
            user.setLogin("admin");
            user.setPassword(hashPassword("12345"));
            user.getRoles().add(JavaFxPro1Application.ROLE.USER.toString());
            user.getRoles().add(JavaFxPro1Application.ROLE.MANAGER.toString());
            user.getRoles().add(JavaFxPro1Application.ROLE.ADMINISTRATOR.toString());
            userRepository.save(user);
        }
    }
}
