package de.jxson.jutils.repository.user;

import de.jxson.jutils.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE username = :username")
    User findByUsername(String username);

}
