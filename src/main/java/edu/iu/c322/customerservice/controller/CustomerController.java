package edu.iu.c322.customerservice.controller;

import edu.iu.c322.customerservice.model.Customer;
import edu.iu.c322.customerservice.repository.InMemoryCustomerRepository;
import edu.iu.c322.customerservice.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository){
        this.repository = repository;
    }

    // get localhost:8080/customers
    @GetMapping
    public List<Customer> findAll(){
        return repository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Customer customer){

        Customer newCustomer = repository.save(customer);
        return newCustomer.getId();
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Customer customer, @PathVariable int id){
        customer.setId(id);
        repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        Customer c = new Customer();
        c.setId(id);
        repository.delete(c);
    }
}
