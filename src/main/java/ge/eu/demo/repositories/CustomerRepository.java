package ge.eu.demo.repositories;

import ge.eu.demo.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("From Customer where concat(firstName, concat(' ', lastName)) like :searchText")
    Page<Customer> search(@Param("searchText") String searchText, Pageable pageable);

}
