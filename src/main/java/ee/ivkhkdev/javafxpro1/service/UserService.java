package ee.ivkhkdev.javafxpro1.service;

import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser save(String firstname, String lastname,String login, String password) {
        AppUser user = new AppUser();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLogin(login);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean authenticate(String login, String password) {
        AppUser user = userRepository.findByLogin(login);
        if(user.getPassword().equals(password)) {
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
            user.setPassword("12345");
            userRepository.save(user);
        }
    }
}
