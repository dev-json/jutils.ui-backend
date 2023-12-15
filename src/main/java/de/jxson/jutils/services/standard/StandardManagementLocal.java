package de.jxson.jutils.services.standard;

import de.jxson.jutils.entity.session.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

public interface StandardManagementLocal {

    ResponseEntity<?> currentTimestamp(HttpServletRequest request);

}
