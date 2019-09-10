package com.uxstudio.contactlist.service.impl;

import com.uxstudio.contactlist.entity.Contact;
import com.uxstudio.contactlist.entity.User;
import com.uxstudio.contactlist.exception.ContactNotFoundException;
import com.uxstudio.contactlist.exception.UserNotFoundException;
import com.uxstudio.contactlist.repository.ContactRepository;
import com.uxstudio.contactlist.repository.UserRepository;
import com.uxstudio.contactlist.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact createContact(long userId, Contact contact) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(long contactId) throws ContactNotFoundException {
        Contact contact = contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        contact.setUser(null);
        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact updateContact(long userId, long contactId, Contact contact) throws UserNotFoundException, ContactNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Contact oldContact = contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
        contact.setId(oldContact.getId());
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(long contactId) throws ContactNotFoundException {
        return contactRepository.findById(contactId).orElseThrow(ContactNotFoundException::new);
    }

    @Override
    public List<Contact> getAllContactsOfUser(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getContacts();
    }
}
