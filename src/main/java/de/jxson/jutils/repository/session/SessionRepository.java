package de.jxson.jutils.repository.session;

import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session findByToken(String token);

    @Query("SELECT s FROM Session s WHERE isValid = true")
    List<Session> findAllValidSessions();

    @Query("SELECT s FROM Session s WHERE isValid = true AND user = :user")
    Session findByUser(User user);

}
