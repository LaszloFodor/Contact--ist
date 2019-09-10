package com.uxstudio.contactlist.service;

import com.uxstudio.contactlist.entity.Contact;
import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.ContactNotFoundException;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContactServiceTest {

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
    public void testCreateContact() throws UserNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.of(user2));
        when(contactRepository.save(any())).thenReturn(contact3);
        assertEquals("testname3", contactService.createContact(1L, contact3).getName());
        assertEquals("testphone3", contactService.createContact(1L, contact3).getPhoneNumber());
        assertEquals("testemail3", contactService.createContact(1L, contact3).getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void testCreateContact_UserNotFound() throws UserNotFoundException {
        when(contactRepository.save(any())).thenReturn(contact3);
        contactService.createContact(1L, contact3);
    }

    @Test
    public void testUpdateContact() throws UserNotFoundException, ContactNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.of(user2));
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact3));
        when(contactRepository.save(any())).thenReturn(contact3);
        assertEquals("testname3", contactService.updateContact(1L, 1L, contact3).getName());
        assertEquals("testphone3", contactService.updateContact(1L, 1L, contact3).getPhoneNumber());
        assertEquals("testemail3", contactService.updateContact(1L, 1L, contact3).getEmail());
    }

    @Test(expected = UserNotFoundException.class)
    public void testUpdateContact_UserNotFound() throws UserNotFoundException, ContactNotFoundException {
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact3));
        when(contactRepository.save(any())).thenReturn(contact3);
        contactService.updateContact(1L, 1L, contact3);
    }

    @Test(expected = ContactNotFoundException.class)
    public void testUpdateContact_ContactNotFound() throws UserNotFoundException, ContactNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.of(user2));
       contactService.updateContact(1L, 1L, contact3);
    }

    @Test
    public void testGetContactById() throws ContactNotFoundException {
        when(contactRepository.findById(any())).thenReturn(Optional.of(contact3));
        assertEquals("testname3", contactService.getContactById(1L).getName());
        assertEquals("testphone3", contactService.getContactById(1L).getPhoneNumber());
        assertEquals("testemail3", contactService.getContactById(1L).getEmail());
    }

    @Test(expected = ContactNotFoundException.class)
    public void testGetContactById_ContactNotFound() throws ContactNotFoundException {
        contactService.getContactById(1L);
    }

    @Test
    public void testGetAllContacts() throws UserNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.of(user1));
        assertEquals(2, contactService.getAllContactsOfUser(1L).size());
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetAllContacts_UserNotFound() throws UserNotFoundException {
        contactService.getAllContactsOfUser(1L);
    }
}
