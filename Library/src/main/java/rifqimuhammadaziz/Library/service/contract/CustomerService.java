package rifqimuhammadaziz.Library.service.contract;

import rifqimuhammadaziz.Library.dto.CustomerDto;
import rifqimuhammadaziz.Library.model.Customer;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);
    Customer findByUsername(String username);
    Customer update(Customer customer);
}
