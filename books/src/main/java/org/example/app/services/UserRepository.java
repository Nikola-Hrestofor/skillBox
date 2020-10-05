package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final Logger logger = Logger.getLogger(UserRepository.class);
    private final List<User> repo = new ArrayList<>();

    public List<User> retreiveAll() {
        return new ArrayList<>(repo);
    }

    public void add(User user) {
        repo.add(user);
    }
}
