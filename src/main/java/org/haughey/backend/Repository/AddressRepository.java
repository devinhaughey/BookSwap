package org.haughey.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.haughey.backend.Entity.Address;
import java.util.Collection;

/**
 * Repository interface to interact with database
 * @author dhaugh
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Collection<Address> findByStreet(String street);
}