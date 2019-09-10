package com.uxstudio.contactlist.service;

import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User createUser(User user);

    void deleteUser(long id);

    User updateUser(long id, User user) throws UserNotFoundException;

    User getUserById(long id) throws UserNotFoundException;

    List<User> getAllUsers();
}
