package com.juliano.apimysql.controller;

import com.juliano.apimysql.config.LogType;
import com.juliano.apimysql.config.Logs;
import com.juliano.apimysql.model.Contact;
import com.juliano.apimysql.model.Persons;
import com.juliano.apimysql.service.ContactService;
import com.juliano.apimysql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"api/v1"})
public class Controller {

    @Autowired
    private Logs logs;

    @Autowired
    PersonService personService;

    @Autowired
    ContactService contactService;

    @GetMapping("/persons")
    public ResponseEntity<List<Persons>> getAllContacts(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        try {
            List<Persons> personsList = new ArrayList<Persons>();
            personService.findAll().forEach(personsList::add);
            if (personsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var responseList =  new ResponseEntity<>(personsList, HttpStatus.OK);
            logs.logRequest(request, headers, responseList, LogType.INFO, null);
            return responseList;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<List<Persons>> getContactsById(
            @PathVariable("id") long id,
            HttpServletRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        try {
            List<Persons> _person = new ArrayList<Persons>();
            personService.findAllById(id);
            if (_person.isEmpty()) {
                throw new Exception(new Error("Pessoa não encontrada."));
            } else{
                var responseList = new ResponseEntity(_person, HttpStatus.OK);
                logs.logRequest(request, headers, responseList, LogType.INFO, null);
                return responseList;
            }
        } catch (Exception e) {
            var _responseEntity =  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
            logs.logRequest(request, headers, _responseEntity, LogType.WARN, "");
            return _responseEntity;
        }
    }

    @PostMapping("/contacts/{id}")
    public ResponseEntity<List<Contact>> createContact(
            @PathVariable("id") long id,
            @RequestBody Contact contact,
            HttpServletRequest request,
            @RequestHeader HttpHeaders headers) {
        try {
            if (Long.toString(id) == null || Long.toString(id) == "") {
                throw new Exception(new Error("É Necessário informar o ID"));
            } else {
                    Persons persons = new Persons();
                    persons.setId(id);
                    contact.setPersons(persons);
                    contactService.save(contact);

//                    List<Persons> personsList = new ArrayList<Persons>();
//                    personService.findById(id).forEach(personsList::add);


                    var responseList =  new ResponseEntity(personService.findById(id), HttpStatus.CREATED);
                    logs.logRequest(request, headers, responseList, LogType.INFO, null);
                    return responseList;
                }
            } catch (Exception e) {
            var _responseEntity =  new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
            logs.logRequest(request, headers, _responseEntity, LogType.WARN, "");
            return _responseEntity;
        }
    }



//
//    @GetMapping("/persons/{id}")
//    public ResponseEntity<List<Persons>> getAllContactsById(@PathVariable("id") long id) {
//        try {
//            List<Optional<Persons>> personsList = new ArrayList<>();
//            personsList.add(personsRepository.findById(id));
//            if (personsList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity(personsList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/persons")
//    public ResponseEntity<Persons> create(@RequestBody Persons persons) {
//        try {
//            return new ResponseEntity(personsRepository.save(persons), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }





//    @GetMapping("/contacts")
//    public ResponseEntity<List<Contact>> getAllContacts() {
//        try {
//            List<Contact> contacts = new ArrayList<Contact>();
//            repository.findAll().forEach(contacts::add);
//            if (contacts.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(contacts, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/contacts/{id}")
//    public ResponseEntity<List<Contact>> getContactsById(@PathVariable("id") long id) {
//        Optional<Contact> contactData = repository.findById(id);
//        if (contactData.isPresent()) {
//            return new ResponseEntity(contactData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @PostMapping("/contacts")
//    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
//        try {
//            return new ResponseEntity<>(repository.save(contact), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PutMapping("/contacts/{id}")
//    public ResponseEntity<Contact> updateContact(@PathVariable("id") long id, @RequestBody Contact contact) {
//        Optional<Contact> contactData = repository.findById(id);
//        if (contactData.isPresent()) {
//            Contact _contact = contactData.get();
//            _contact.setName(contact.getName());
//            _contact.setEmail(contact.getEmail());
//            _contact.setPhone(contact.getPhone());
//            return new ResponseEntity<>(repository.save(_contact), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/contacts/{id}")
//    public ResponseEntity<HttpStatus> deleteContact(@PathVariable("id") long id) {
//        try {
//            repository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity(new Erro("ID Não Encontrado."),  HttpStatus.NOT_FOUND);
//        }
//    }

//    class Erro{
//        public String message;
//        public Erro(String message) {
//            this.message = message;
//        }
//    }
}