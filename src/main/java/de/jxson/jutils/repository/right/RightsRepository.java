package de.jxson.jutils.repository.right;

import de.jxson.jutils.entity.right.Rights;
import de.jxson.jutils.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightsRepository extends JpaRepository<Rights, Long> {

    Rights findByRightName(String name);

}
