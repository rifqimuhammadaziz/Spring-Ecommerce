package rifqimuhammadaziz.Admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"rifqimuhammadaziz.Library.*", "rifqimuhammadaziz.Admin.*"})
@EnableJpaRepositories(value = "rifqimuhammadaziz.Library.repository")
@EntityScan(value = "rifqimuhammadaziz.Library.model")
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
