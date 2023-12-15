package de.jxson.jutils.services.roles;

import de.jxson.jutils.entity.role.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface RolesManagementLocal {

    /* Roles */
    ResponseEntity<?> createRole(Role role, HttpServletRequest request);
    ResponseEntity<?> deleteRole(String name, HttpServletRequest request);

    ResponseEntity<?> getRole(String name, HttpServletRequest request);
    ResponseEntity<?> getAllRoles(HttpServletRequest request);

    /* Rights */
    ResponseEntity<?> addRight(String right, HttpServletRequest request);
    ResponseEntity<?> deleteRight(String right, HttpServletRequest request);

}
