package de.jxson.jutils.services.right;

import de.jxson.jutils.entity.right.Rights;
import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.repository.right.RightsRepository;
import de.jxson.jutils.repository.session.SessionRepository;
import de.jxson.jutils.utils.JUtilsResponse;
import de.jxson.jutils.utils.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rights")
@CrossOrigin(origins = "*", exposedHeaders = "JToken")
public class RightManagement implements RightManagementLocal {

    @Autowired
    RightsRepository rightsRepository;
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/add")
    @Override
    public ResponseEntity<?> createRight(@RequestBody Rights rights, HttpServletRequest request) {
        if(request.getHeader("JToken") != null)
        {
            Session session = sessionRepository.findByToken(request.getHeader("JToken"));
            if(session == null || !Token.validateToken(session, sessionRepository))
                return JUtilsResponse.unauthorized("Invalid Token");
        }

        if(rights == null || rights.getRightName() == null || rights.getRightName().isEmpty())
            return JUtilsResponse.badRequest("Right name must contain at least one character!");

        Optional<Rights> optionalRights = rightsRepository.findById((long) rights.getId());

        if(optionalRights.isPresent())
            return JUtilsResponse.badRequest("Right already exists!");

        Rights finalRight = new Rights();
        finalRight.setId(rights.getId());
        finalRight.setRightName(rights.getRightName());
        finalRight.setRightDescription(rights.getRightDescription().isBlank() ? "" : rights.getRightDescription());
        rightsRepository.saveAndFlush(finalRight);
        return JUtilsResponse.ok(finalRight);
    }

    @DeleteMapping("/{rightId}")
    @Override
    public ResponseEntity<?> deleteRight(long id, HttpServletRequest request) {
        return null;
    }

}
