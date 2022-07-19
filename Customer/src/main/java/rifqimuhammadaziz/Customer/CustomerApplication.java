package rifqimuhammadaziz.Customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "rifqimuhammadaziz.Library.*")
@EnableJpaRepositories(value = "rifqimuhammadaziz.Library.repository")
@EntityScan(value = "rifqimuhammadaziz.Library.model")
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
