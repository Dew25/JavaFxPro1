package ee.ivkhkdev.javafxpro1.service;

import ee.ivkhkdev.javafxpro1.entity.AppUser;
import ee.ivkhkdev.javafxpro1.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser addUser(String username, String password) {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        return appUserRepository.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

}
