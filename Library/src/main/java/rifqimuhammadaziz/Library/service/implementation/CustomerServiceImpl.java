package rifqimuhammadaziz.Library.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rifqimuhammadaziz.Library.dto.CustomerDto;
import rifqimuhammadaziz.Library.model.Customer;
import rifqimuhammadaziz.Library.repository.CustomerRepository;
import rifqimuhammadaziz.Library.repository.RoleRepository;
import rifqimuhammadaziz.Library.service.contract.CustomerService;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));

        Customer saveCustomer = customerRepository.save(customer);
        return mapperDTO(saveCustomer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer update(Customer customer) {
        Customer currentCustomer = customerRepository.findByUsername(customer.getUsername());
        currentCustomer.setAddress(customer.getAddress());
        currentCustomer.setCity(customer.getCity());
        currentCustomer.setCountry(customer.getCountry());
        currentCustomer.setPhoneNumber(customer.getPhoneNumber());
        System.out.println(currentCustomer);

        return customerRepository.save(currentCustomer);
    }

    private CustomerDto mapperDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        return customerDto;
    }
}
