package org.haughey.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.haughey.backend.Entity.Book;
import java.util.Collection;

/**
 * Repository interface to interact with db (can be autowired)
 * @author dhaugh
 */
//@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Integer> {
    Collection<Book> findByTitle(String title);
}