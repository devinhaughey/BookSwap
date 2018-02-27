package org.haughey.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.haughey.backend.Entity.UserProfile;
import java.util.Collection;

/**
 * Repository interface to interact with database
 * @author dhaugh
 */
public interface UserRepository extends JpaRepository<UserProfile, Integer> {

    Collection<UserProfile> findByFirstName(String first_name);
}