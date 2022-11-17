package com.labestia.finalassessmentjavabackend.service;

import com.labestia.finalassessmentjavabackend.domain.entity.User;
import com.labestia.finalassessmentjavabackend.repository.UserJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserJpaRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User createUser(User user) {
        if(checkInput(user)) {
            return repository.save(user);
        }

        return null;
    }

    private boolean checkInput(User user) {
        return !user.getFirstName().isEmpty()
                && !user.getLastName().isEmpty()
                && user.getEmail().length() > 0
                && user.getEmail().toLowerCase().matches("^([a-z])(\\w+)@([a-z]+)\\.([a-z]+)$")
                && user.getPhoneNumber().length() > 0 && user.getPhoneNumber().matches("^(\\d+)$");
    }
}
