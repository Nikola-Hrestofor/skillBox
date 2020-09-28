package org.example.app.services;

import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpService {
    private final UserRepository userRepo;

    @Autowired
    public SignUpService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.retreiveAll();
    }

    public boolean addUser(String user, String password, String password2) {
        if(password.equals(password2)){
            userRepo.add(new User(user, password));
            return true;
        }
        return false;
    }
}
