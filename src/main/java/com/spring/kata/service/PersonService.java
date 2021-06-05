package com.spring.kata.service;

import com.spring.kata.repository.Person;
import com.spring.kata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(String objectId) {
        return personRepository.findById(Long.valueOf(objectId)).orElseThrow(()->new NoSuchElementException("No element was found for this id."));
    }

    public String createPerson(Person person) {
        personRepository.save(person);
        return "Person was created.";
    }

    public String updatePerson(String objectId, Person person) {
        person.setId(Long.valueOf(objectId));
        personRepository.save(person);
        return "Person was updated.";
    }

    public String deletePerson(String objectId) {
        Person person = getPersonById(objectId);
        personRepository.delete(person);
        return "Person was deleted.";
    }
}
