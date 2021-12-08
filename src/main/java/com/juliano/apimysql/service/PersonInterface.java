package com.juliano.apimysql.service;

import com.juliano.apimysql.model.Persons;

import java.util.List;
import java.util.Optional;

public interface PersonInterface {
    Optional<Persons> findById(Long id);

    List<Persons> findAll();

    Persons save(Persons persons);
}
