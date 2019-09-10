package com.uxstudio.contactlist.service;

import com.uxstudio.contactlist.entity.Contact;
import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.UserNotFoundException;
import com.uxstudio.contactlist.repository.ContactRepository;
import com.uxstudio.contactlist.repository.UserRepository;
import com.uxstudio.contactlist.service.impl.ContactServiceImpl;
import com.uxstudio.contactlist.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private ContactServiceImpl contactService;

    private User user1;

    private User user2;

    private Contact contact1;

    private Contact contact2;

    private Contact contact3;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contact1 = new Contact("testname", "testphone", "testemail", user1);
        contact2 = new Contact("testname2", "testphone2", "testemail2", user1);
        contact3 = new Contact("testname3", "testphone3", "testemail3", user2);
        user1 = new User("testuser1", Arrays.asList(contact1, contact2));
        user2 = new User("testuser2", Arrays.asList(contact3));
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any())).thenReturn(user2);
        assertEquals("testuser2", userService.createUser(user2).getName());
        assertEquals("testname3", userService.createUser(user2).getContacts().get(0).getName());
        assertEquals("testemail3", userService.createUser(user2).getContacts().get(0).getEmail());
        assertEquals("testphone3", userService.createUser(user2).getContacts().get(0).getPhoneNumber());
    }

    @Test
    public void testUpdateUser() {
        User testUser = null;
        Contact testContact = new Contact("update", "update", "update", testUser);
        testUser = new User("update", Arrays.asList(testContact));
        when(userRepository.save(any())).thenReturn(testUser);
        assertEquals("update", userService.updateUser(1l, new User()).getName());
        assertEquals("update", userService.updateUser(1l, new User()).getContacts().get(0).getName());
        assertEquals("update", userService.updateUser(1l, new User()).getContacts().get(0).getPhoneNumber());
        assertEquals("update", userService.updateUser(1l, new User()).getContacts().get(0).getEmail());
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.of(user1));
        assertEquals("testuser1", userService.getUserById(1L).getName());
        assertEquals("testname", userService.getUserById(1L).getContacts().get(0).getName());
        assertEquals("testphone", userService.getUserById(1L).getContacts().get(0).getPhoneNumber());
        assertEquals("testemail", userService.getUserById(1L).getContacts().get(0).getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserById_NotFound() throws UserNotFoundException {
        userService.getUserById(1L);
    }

    @Test
    public void testGetUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        assertEquals(2, userService.getAllUsers().size());
        assertEquals("testuser1", userService.getAllUsers().get(0).getName());
        assertEquals("testuser2", userService.getAllUsers().get(1).getName());
    }



}
