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

    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return  ResponseEntity.ok(customers);
    }

    public ResponseEntity<Customer> addCustomer(Customer customer) {
        Customer addcustomer = customerRepository.save(customer);
        return ResponseEntity.ok(addcustomer);
    }

    public ResponseEntity<Customer> findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer does not exit"));
        return ResponseEntity.ok(customer);
    }

    public ResponseEntity<Map<String, Boolean>> deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Customer does not exit"));
        customerRepository.deleteById(id);
        Map<String, Boolean> deleteEmployee = new HashMap<String, Boolean>();
        deleteEmployee.put("Customer deleted Successfully", Boolean.TRUE);
        return ResponseEntity.ok(deleteEmployee);
    }

    public ResponseEntity<Customer> editCustomer(Integer id, Customer customer){
        Customer findCustomer = customerRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer does not exit"));

        findCustomer.setFirstName(customer.getFirstName());
        findCustomer.setLastName(customer.getLastName());
        findCustomer.setEmail(customer.getEmail());

        Customer updatedCustomer = customerRepository.save(findCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }
}
