package example.demo;

import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import example.demo.Entity.RoleEntity;
import example.demo.Entity.UserEntity;
import example.demo.Repository.RoleRepository;
import example.demo.Repository.UserRepository;

@SpringBootApplication
public class DemoSpringbootsWebCrud1Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootsWebCrud1Application.class, args);

	}
}
