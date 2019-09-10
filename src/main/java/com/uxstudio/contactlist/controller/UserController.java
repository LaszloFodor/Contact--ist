package com.uxstudio.contactlist.controller;

import com.uxstudio.contactlist.entity.Contact;
import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.ContactNotFoundException;
import com.uxstudio.contactlist.exception.UserNotFoundException;
import com.uxstudio.contactlist.service.impl.ContactServiceImpl;
import com.uxstudio.contactlist.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ContactServiceImpl contactService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) throws UserNotFoundException {
        return userService.getUserById(Long.valueOf(id));
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) throws UserNotFoundException {
        return userService.updateUser(Long.valueOf(id), user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(Long.valueOf(id));
        return "User successfully deleted.";
    }

    @PostMapping("/{id}/contacts")
    public Contact createContact(@PathVariable String id, @RequestBody Contact contact) throws UserNotFoundException {
        return contactService.createContact(Long.valueOf(id), contact);
    }

    @DeleteMapping("/{userId}/contacts/{contactId}")
    public String deleteContact(@PathVariable String userId, @PathVariable String contactId) throws ContactNotFoundException {
        contactService.deleteContact(Long.valueOf(contactId));
        return "Contact successfully deleted.";
    }

    @PutMapping("/{userId}/contacts/{contactId}")
    public Contact updateContact(@PathVariable String userId, @PathVariable String contactId,  @RequestBody Contact contact) throws UserNotFoundException, ContactNotFoundException {
        return contactService.updateContact(Long.valueOf(userId), Long.valueOf(contactId), contact);
    }

    @GetMapping("/{userId}/contacts/{contactId}")
    public Contact getContactById(@PathVariable String contactId) throws ContactNotFoundException {
        // todo: implement contact id
        return contactService.getContactById(Long.valueOf(contactId));
    }

    @GetMapping("/{userId}/contacts")
    public List<Contact> getContacts(@PathVariable String userId) throws UserNotFoundException {
        return contactService.getAllContactsOfUser(Long.valueOf(userId));
    }
}
