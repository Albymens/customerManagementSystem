package dreamOvalTest.customerManagementSystem.controller;

import dreamOvalTest.customerManagementSystem.entity.Customer;
import dreamOvalTest.customerManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> listOfAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Integer id){
        return customerService.findCustomerById(id);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable("id") Integer id){
        return customerService.deleteCustomer(id);
    }
}
