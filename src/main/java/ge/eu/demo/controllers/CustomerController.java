package ge.eu.demo.controllers;

import ge.eu.demo.dto.CustomerData;
import ge.eu.demo.dto.RequestData;
import ge.eu.demo.dto.SearchCustomer;
import ge.eu.demo.entities.Customer;
import ge.eu.demo.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Customer getOne(@PathVariable Long id) {
        return customerService.getOne(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    private Customer addCustomer(@RequestBody CustomerData customerData) {
        return customerService.addEditCustomer(customerData, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    private Customer addCustomer(@RequestBody CustomerData customerData, @PathVariable Long id) {
        return customerService.addEditCustomer(customerData, id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    private Page<Customer> searchCustomer(@RequestBody RequestData<SearchCustomer> rd) {
        return customerService.search(rd.getData(), rd.getPaging());
    }
}
