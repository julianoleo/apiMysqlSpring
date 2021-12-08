package com.juliano.apimysql.service;

import com.juliano.apimysql.model.Persons;
import com.juliano.apimysql.model.PersonsDTO;
import com.juliano.apimysql.repository.PersonsRepository;
import com.juliano.apimysql.repository.PersonsRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService implements PersonInterface {

    @Autowired
    PersonsRepository personsRepository;

    @Autowired
    PersonsRepositoryDTO personsRepositoryDTO;

    @Override
    public Optional<Persons> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Persons> findAll(){
        List<Persons> result = new ArrayList<>();
        personsRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Persons save(Persons persons){
        return personsRepository.save(persons);
    }

    public Set<Persons> buscaPerson(Long id){
        Set<Persons> result = new HashSet<>();
        result.add(personsRepositoryDTO.findById(id));
    }

}
