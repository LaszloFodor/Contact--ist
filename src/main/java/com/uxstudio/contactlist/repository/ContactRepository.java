package com.uxstudio.contactlist.repository;

import com.uxstudio.contactlist.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> getAllByUser(long id);
}
