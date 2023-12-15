package de.jxson.jutils.repository.role;

import de.jxson.jutils.entity.role.Role;
import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
