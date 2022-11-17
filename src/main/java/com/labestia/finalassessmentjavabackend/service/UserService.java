package com.labestia.finalassessmentjavabackend.service;

import com.labestia.finalassessmentjavabackend.domain.entity.User;
import com.labestia.finalassessmentjavabackend.repository.UserJpaRepository;
import java.util.List;
import java.util.Optional;
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
        return !isNullOrEmpty(user.getFirstName())
                && !isNullOrEmpty(user.getLastName())
                && !isNullOrEmpty(user.getEmail())
                && user.getEmail().toLowerCase().matches("^([a-z])(\\w+)@([a-z]+)\\.([a-z]+)$")
                && !isNullOrEmpty(user.getPhoneNumber()) && user.getPhoneNumber().matches("^(\\d+)$");
    }

    public User updateUser(User updatedUser) {
        Optional<User> userDb = repository.findById(updatedUser.getId());
        if(userDb.isEmpty())
        {
            return null;
        }
        else {
            User user = userDb.get();
            if(!isNullOrEmpty(updatedUser.getFirstName()))
                user.setFirstName(updatedUser.getFirstName());
            if(!isNullOrEmpty(updatedUser.getLastName()))
                user.setLastName(updatedUser.getLastName());
            if(!isNullOrEmpty(updatedUser.getEmail()) && updatedUser.getEmail().toLowerCase().matches("^([a-z])(\\w+)@([a-z]+)\\.([a-z]+)$"))
                user.setEmail(updatedUser.getEmail());
            if(!isNullOrEmpty(updatedUser.getPhoneNumber()) && updatedUser.getPhoneNumber().matches("^(\\d+)$"))
                user.setPhoneNumber(updatedUser.getPhoneNumber());

            return repository.save(user);
        }
    }

    public void deleteUser(int id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent())
            repository.deleteById(id);
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
