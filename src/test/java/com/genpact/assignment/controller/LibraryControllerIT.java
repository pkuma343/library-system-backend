package com.genpact.assignment.controller;

import com.genpact.assignment.Application;
import com.genpact.assignment.models.Book;
import com.genpact.assignment.models.Library;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryControllerIT {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveBooks() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/libraries/10001/books"),
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSaveBook() throws JSONException {
        Book book = new Book("My Book");
        HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/libraries/10001/books"),
                HttpMethod.POST, entity, String.class);
        Assert.assertNotNull(response);
    }

    @Test
    public void testUpdateBook() throws JSONException {
        Book book = new Book("Updated Book");
        HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/libraries/10001/books/11001"),
                HttpMethod.PUT, entity, String.class);
        Assert.assertNotNull(response);
    }

    @Test
    public void testRetrieveLibraries() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/libraries"),
                HttpMethod.GET, entity, String.class);
        Assert.assertNotNull(response);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
