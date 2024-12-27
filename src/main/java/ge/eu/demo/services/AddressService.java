package ge.eu.demo.services;

import ge.eu.demo.dto.AddAddress;
import ge.eu.demo.entities.Address;
import ge.eu.demo.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getByDistrict(String district) {
        return addressRepository.findAllByDistrict(district);
    }

    @Transactional
    public Address addAddress(AddAddress addData) throws Exception {
        Address added = addressRepository.findOneByDistrictAndAddress(addData.getDistrict(), addData.getAddress());
        if (added != null) {
           throw new Exception("already_added");
        }

        Address address = new Address();
        address.setAddress(addData.getAddress());
        address.setDistrict(addData.getDistrict());

        return addressRepository.save(address);
    }
}
