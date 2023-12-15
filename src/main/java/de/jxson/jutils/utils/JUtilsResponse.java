package de.jxson.jutils.utils;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class JUtilsResponse<T> extends ResponseEntity<T> {

    public JUtilsResponse(T body, HttpStatusCode status) {
        super(body, status);
    }

    public static ResponseEntity<?> badRequest(String message)
    {
        return JUtilsResponse.badRequest().contentType(MediaType.APPLICATION_JSON).body("{\"message\": \""+message+"\"}");
    }

    public static ResponseEntity<?> unauthorized(String message)
    {
        return JUtilsResponse.status(401).contentType(MediaType.APPLICATION_JSON).body("{\"message\": \""+message+"\"}");
    }

    public static ResponseEntity<?> ok(String message)
    {
        return JUtilsResponse.status(200).contentType(MediaType.APPLICATION_JSON).body("{\"message\": \""+message+"\"}");
    }


}
