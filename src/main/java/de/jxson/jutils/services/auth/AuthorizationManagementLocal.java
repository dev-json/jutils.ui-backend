package de.jxson.jutils.services.auth;

import de.jxson.jutils.entity.user.User;
import org.springframework.http.ResponseEntity;

public interface AuthorizationManagementLocal {

    ResponseEntity<?> login(User user);
    ResponseEntity<?> register(User user);

}
