package com.uxstudio.contactlist.service.impl;

import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.UserNotFoundException;
import com.uxstudio.contactlist.repository.UserRepository;
import com.uxstudio.contactlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
