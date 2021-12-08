package com.juliano.apimysql.service;

import com.juliano.apimysql.model.Contact;

import java.util.Optional;

public interface ContactInterface {
    Optional<Contact> findById(Long id);
    Contact save(Contact contact);
}
