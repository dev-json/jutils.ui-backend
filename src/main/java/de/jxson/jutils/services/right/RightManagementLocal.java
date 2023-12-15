package de.jxson.jutils.services.right;

import de.jxson.jutils.entity.right.Rights;
import de.jxson.jutils.entity.role.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface RightManagementLocal {

    ResponseEntity<?> createRight(Rights rights, HttpServletRequest request);
    ResponseEntity<?> deleteRight(long id, HttpServletRequest request);

}
