package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.example.web.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);

    private final UserRepository users;

    @Autowired
    public LoginService(UserRepository users) {
        this.users = users;
    }

    public boolean authenticate(LoginForm loginFrom) {
        for (User user : users.retreiveAll()) {
            if(loginFrom.getUsername().equals(user.getName()) && loginFrom.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        logger.info("try auth with user-form: " + loginFrom);
        return false;
    }
}
