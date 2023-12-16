package de.jxson.jutils.services.roles;

import de.jxson.jutils.entity.right.Rights;
import de.jxson.jutils.entity.role.Role;
import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.repository.right.RightsRepository;
import de.jxson.jutils.repository.role.RoleRepository;
import de.jxson.jutils.repository.session.SessionRepository;
import de.jxson.jutils.utils.JUtilsResponse;
import de.jxson.jutils.utils.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolesManagement implements RolesManagementLocal {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    RightsRepository rightsRepository;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> createRole(@RequestBody Role role, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        if(role == null || role.getName() == null || role.getName().isEmpty())
            return JUtilsResponse.badRequest("Role name must contain at least one character!");

        Role finalRole = roleRepository.findByName(role.getName());

        if(finalRole != null)
                return JUtilsResponse.badRequest("Role already exists!");


        finalRole = new Role();
        finalRole.setName(role.getName());
        finalRole.setPriority(role.getPriority());
        roleRepository.saveAndFlush(finalRole);
        return JUtilsResponse.ok(finalRole);
    }

    @DeleteMapping("/{roleName}")
    @Override
    public ResponseEntity<?> deleteRole(@PathVariable String roleName, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }
        Role role = roleRepository.findByName(roleName);
        if(role == null)
            return JUtilsResponse.badRequest("Role not found");

        roleRepository.delete(role);
        return JUtilsResponse.ok("Role deleted");
    }

    @GetMapping("/{roleName}")
    @Override
    public ResponseEntity<?> getRole(@PathVariable String roleName, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            return JUtilsResponse.badRequest("Role not found");
        return JUtilsResponse.accepted().contentType(MediaType.APPLICATION_JSON).body(role);
    }

    @GetMapping("/roles")
    @Override
    public ResponseEntity<?> getAllRoles(HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        return JUtilsResponse.ok(roleRepository.findAll());
    }

    @PostMapping("/{roleName}/right")
    @Override
    public ResponseEntity<?> addRight(@PathVariable String roleName, @RequestBody Rights right, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            return JUtilsResponse.badRequest("Role not found");

        Rights optionalRights = rightsRepository.findById((long) right.getId()).orElse(null);
        if(optionalRights == null)
            return JUtilsResponse.badRequest("Right not found");

        if(role.getRights().stream().map(Rights::getId).toList().contains(optionalRights.getId()))
            return JUtilsResponse.badRequest("Role already has the right");

        role.getRights().add(optionalRights);
        roleRepository.saveAndFlush(role);
        return JUtilsResponse.ok(role);
    }

    @DeleteMapping("/{roleName}/{rightId}")
    @Override
    public ResponseEntity<?> deleteRight(@PathVariable String roleName, @PathVariable int rightId, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        Role role = roleRepository.findByName(roleName);
        if(role == null)
            return JUtilsResponse.badRequest("Role not found");

        Rights optionalRights = rightsRepository.findById((long) rightId).orElse(null);
        if(optionalRights == null)
            return JUtilsResponse.badRequest("Right not found");

        if(!role.getRights().stream().map(Rights::getId).toList().contains(optionalRights.getId()))
            return JUtilsResponse.badRequest("Role didn't has this right");

        role.getRights().remove(optionalRights);
        roleRepository.saveAndFlush(role);
        return JUtilsResponse.ok(role);
    }
}
