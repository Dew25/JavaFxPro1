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

    public AppUser add(String firstName, String lastName) {
        AppUser user = new AppUser();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        return userRepository.save(user);

    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}
