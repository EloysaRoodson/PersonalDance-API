package com.Eloysa.personal_dance.exeception;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void handleConflict_nullRootCause_shouldNotThrow() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        DataIntegrityViolationException ex = new DataIntegrityViolationException("constraint violation");
        ResponseEntity<Map<String,String>> res = handler.handleConflict(ex);
        assertEquals(HttpStatus.CONFLICT, res.getStatusCode());
        assertNotNull(res.getBody());
        assertTrue(res.getBody().get("error").contains("Operação inválida"));
    }
}
