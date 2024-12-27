package ge.eu.demo.controllers;

import ge.eu.demo.dto.AddAddress;
import ge.eu.demo.entities.Address;
import ge.eu.demo.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Address getById(@PathVariable Long id) {
        return addressService.getById(id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/json"})
    public List<Address> getAllByDistrict(@RequestParam("district") String district) {
        return addressService.getByDistrict(district);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public Address add(@RequestBody AddAddress addAddress) throws Exception {
        return addressService.addAddress(addAddress);
    }
}
