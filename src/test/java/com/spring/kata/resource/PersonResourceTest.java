package com.spring.kata.resource;

import com.spring.kata.repository.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PersonResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getAllPersons() throws Exception {
        ResponseEntity<Person[]> response =
                restTemplate.getForEntity("/persons", Person[].class);

        //Verify OK status and not empty body
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(true, response.getBody().length > 0);
    }

    @Test
    void getPersonById() {
        ResponseEntity<Person> response =
                restTemplate.getForEntity("/persons/1", Person.class);

        //Verify OK status and not empty body
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(Long.valueOf("1"), response.getBody().getId());
    }

    @Test
    void createPerson() {
        Person employee = new Person("Adam", "Adam Adress");
        HttpEntity<Person> request = new HttpEntity<>(employee, new HttpHeaders());

        ResponseEntity<String> result = this.restTemplate.postForEntity("/persons", request, String.class);

        //Verify bad request and missing header
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(true, result.getBody().contains("Person was created."));
    }

    @Test
    void updatePerson() {
        Person employee = new Person("Adam", "Adam Adress");
        HttpEntity<Person> request = new HttpEntity<>(employee, new HttpHeaders());

        ResponseEntity<String> result = restTemplate
                .exchange("/persons/1", HttpMethod.PUT, request, String.class);

        //Verify bad request and missing header
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(true, result.getBody().contains("Person was updated."));
    }

    @Test
    void deletePerson() {
        HttpEntity<Person> request = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> result = restTemplate
                .exchange("/persons/2", HttpMethod.DELETE, request, String.class);

        //Verify OK status and not empty body
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(true, result.getBody().contains("Person was deleted."));
    }
}