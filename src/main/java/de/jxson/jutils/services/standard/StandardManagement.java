package de.jxson.jutils.services.standard;

import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.repository.session.SessionRepository;
import de.jxson.jutils.services.auth.AuthorizationManagement;
import de.jxson.jutils.utils.JUtilsResponse;
import de.jxson.jutils.utils.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/standard")
@CrossOrigin
public class StandardManagement implements StandardManagementLocal {

    @GetMapping("/currentTimestamp")
    @Override
    public ResponseEntity<?> currentTimestamp(HttpServletRequest request) {
        return ResponseEntity.accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(String.valueOf(System.currentTimeMillis()));
    }
}
