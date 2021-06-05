package com.spring.kata.resource;

import com.spring.kata.repository.Person;
import com.spring.kata.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin//(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/persons")
public class PersonResource {
    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Person getPersonById(@PathVariable("id") String objectId) {
        return personService.getPersonById(objectId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        String message = personService.createPerson(person);
        return ResponseEntity.ok().body(message);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") String objectId, @RequestBody Person person) {
        String message = personService.updatePerson(objectId, person);
        return ResponseEntity.ok().body(message);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") String objectId) {
        String message = personService.deletePerson(objectId);
        return ResponseEntity.ok().body(message);
    }
}
