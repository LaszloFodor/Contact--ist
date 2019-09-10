package com.uxstudio.contactlist.service;

import com.uxstudio.contactlist.entity.Contact;
import com.uxstudio.contactlist.exception.ContactNotFoundException;
import com.uxstudio.contactlist.exception.UserNotFoundException;

import java.util.List;

public interface ContactService {

    Contact createContact(long userId, Contact contact) throws UserNotFoundException;

    void deleteContact(long contactId) throws ContactNotFoundException;

    Contact updateContact(long userId, long contactId, Contact contact) throws UserNotFoundException, ContactNotFoundException;

    Contact getContactById(long contactId) throws ContactNotFoundException;

    List<Contact> getAllContactsOfUser(long userId) throws UserNotFoundException;
}
