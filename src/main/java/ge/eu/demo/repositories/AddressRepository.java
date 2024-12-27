package ge.eu.demo.repositories;

import ge.eu.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByDistrict(String district);

    Address findOneByDistrictAndAddress(String district, String address);

}
