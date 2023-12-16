package de.jxson.jutils.services.auth;

import de.jxson.jutils.JutilsApplication;
import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.entity.user.User;
import de.jxson.jutils.repository.session.SessionRepository;
import de.jxson.jutils.repository.user.UserRepository;
import de.jxson.jutils.utils.JUtilsResponse;
import de.jxson.jutils.utils.token.Token;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthorizationManagement implements AuthorizationManagementLocal {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;
    private static MessageDigest messageDigest;

    private final Logger log = Logger.getLogger(AuthorizationManagement.class.getSimpleName());

    public AuthorizationManagement()
    {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<?> login(@RequestBody User bodyUser) {

        User user = userRepository.findByUsername(bodyUser.getUsername());

        if(user == null)
            return JUtilsResponse.badRequest("User does not exists!");

        byte[] passwordInBytes = bodyUser.getPassword().getBytes();
        messageDigest.update(passwordInBytes);

        String encryptedPass = new BigInteger(1, messageDigest.digest()).toString(16);

        if(!encryptedPass.equals(user.getPassword()))
            return JUtilsResponse.badRequest("Invalid password!");

        Session session = sessionRepository.findByUser(user);

        if(session != null)
        {
            if(!Token.validateToken(session, sessionRepository))
                session = Token.generateSession(user, sessionRepository);
        }
        else
        {
            session = Token.generateSession(user, sessionRepository);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).header("JToken", session.getToken()).body("{\"message\": \"Authorized\"}");
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<?> register(@RequestBody User user) {

        if(user.getUsername().length() < 5)
            return JUtilsResponse.badRequest("Username must be at least 5 characters long!");

        if(user.getPassword().length() < 5)
            return JUtilsResponse.badRequest("Password must be at least 5 characters long!");

        if(userRepository.findByUsername(user.getUsername()) != null)
            return JUtilsResponse.badRequest("User already exists!");

        byte[] passwordInBytes = user.getPassword().getBytes();
        messageDigest.update(passwordInBytes);

        String encryptedPass = new BigInteger(1, messageDigest.digest()).toString(16);

        User finalUser = new User();
        finalUser.setUsername(user.getUsername());
        finalUser.setPassword(encryptedPass);
        userRepository.saveAndFlush(finalUser);

        return ResponseEntity.ok(finalUser);
    }
}
