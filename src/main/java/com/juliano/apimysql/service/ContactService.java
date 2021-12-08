package com.juliano.apimysql.service;

import com.juliano.apimysql.model.Contact;
import com.juliano.apimysql.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService implements ContactInterface {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact save(Contact contact){
        return contactRepository.save(contact);
    }


}
