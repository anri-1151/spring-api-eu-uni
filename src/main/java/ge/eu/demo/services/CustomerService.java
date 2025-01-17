package ge.eu.demo.services;

import ge.eu.demo.dto.AddAddress;
import ge.eu.demo.dto.CustomerData;
import ge.eu.demo.dto.Paging;
import ge.eu.demo.dto.SearchCustomer;
import ge.eu.demo.entities.Address;
import ge.eu.demo.entities.Customer;
import ge.eu.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getOne(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer addEditCustomer(CustomerData data, Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(data.getFirstName());
        customer.setLastName(data.getLastName());

        Address address = this.addressService.addAddress(new AddAddress(data.getAddress(), data.getDistrict()));

        customer.setAddress(address);

        return customerRepository.save(customer);
    }

    public Page<Customer> search(SearchCustomer searchCustomer, Paging paging) {
        String searchText = searchCustomer.getSearchText() != null ? "%" + searchCustomer.getSearchText() + "%" : null;
        Pageable pageable = PageRequest.of(paging.getPage() - 1, paging.getSize(), Sort.by("id").descending());
        return customerRepository.search(searchText, pageable);
    }
}
