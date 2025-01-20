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

    public String addUser(String username, String password) {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(password);
        AppUser appUserFromDb = appUserRepository.save(appUser);
        AppUser appUserReturnDb = appUserRepository.findById(appUserFromDb.getId()).get();
        return String.format("id:%d username:%s password:%s%n",
                appUserReturnDb.getId(),
                appUserReturnDb.getUsername(),
                appUserReturnDb.getPassword());
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }


}
