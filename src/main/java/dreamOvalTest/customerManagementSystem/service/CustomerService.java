package dreamOvalTest.customerManagementSystem.service;

import dreamOvalTest.customerManagementSystem.entity.Customer;
import dreamOvalTest.customerManagementSystem.repository.CustomerRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ResponseEntity responseEntity;

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return  responseEntity.ok(customers);
    }

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        Customer addcustomer = customerRepository.save(customer);
        return responseEntity.ok(addcustomer);
    }

    public ResponseEntity<Customer> findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        return responseEntity.ok(customer);
    }

    public ResponseEntity<Map<String, Boolean>> deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer does not exit"));
        customerRepository.deleteById(id);
        Map<String, Boolean> deleteEmployee = new HashMap<String, Boolean>();
        deleteEmployee.put("Customer deleted Successfully", Boolean.TRUE);
        return responseEntity.ok(deleteEmployee);
    }
}
