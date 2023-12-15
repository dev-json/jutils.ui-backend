package de.jxson.jutils.services.standard;

import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.repository.session.SessionRepository;
import de.jxson.jutils.services.auth.AuthorizationManagement;
import de.jxson.jutils.utils.JUtilsResponse;
import de.jxson.jutils.utils.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/standard")
public class StandardManagement implements StandardManagementLocal {

    @Autowired
    SessionRepository sessionRepository;

    @GetMapping("/currentTimestamp")
    @Override
    public ResponseEntity<?> currentTimestamp(HttpServletRequest request) {

        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        return ResponseEntity.ok(String.valueOf(System.currentTimeMillis()));
    }
}
