package example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import example.demo.Repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class DemoSpringbootsWebCrud1Application {
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootsWebCrud1Application.class, args);

	}
}
