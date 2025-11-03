package br.com.fatec.atv2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DisciplinasControllernTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetAllDisciplinas() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> response = restTemplate.getForEntity("/disciplinas", List.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testGetDisciplineById() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.getForEntity("/disciplinas/1", Map.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().get("id"));
        assertEquals("Integração e entrega contínua", response.getBody().get("name"));
    }

    @Test
    void testCreateDiscipline() {
        Map<String, String> novaDisciplina = Map.of("name", "Engenharia de Software");
        
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity("/disciplinas", novaDisciplina, Map.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("3", response.getBody().get("id"));
        assertEquals("Engenharia de Software", response.getBody().get("name"));
    }
}