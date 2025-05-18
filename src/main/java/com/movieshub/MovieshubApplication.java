package com.movieshub;

import com.movieshub.model.Role;
import com.movieshub.model.User;
import com.movieshub.repository.RoleRepository;
import com.movieshub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
public class MovieshubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieshubApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDatabase(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			// Create ROLE_ADMIN if it doesn't exist
			Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
				Role newRole = new Role();
				newRole.setName("ROLE_ADMIN");
				return roleRepository.save(newRole);
			});

			// Create ROLE_USER if it doesn't exist
			Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
				Role newRole = new Role();
				newRole.setName("ROLE_USER");
				return roleRepository.save(newRole);
			});

			// Create admin user if it doesn't exist
			Optional<User> adminUserOptional = userRepository.findByUsername("admin");
			if (adminUserOptional.isEmpty()) {
				User adminUser = new User();
				adminUser.setUsername("admin");
				adminUser.setPassword(passwordEncoder.encode("admin")); // Password is "admin"
				Set<Role> roles = new HashSet<>();
				roles.add(adminRole);
				adminUser.setRoles(roles);
				userRepository.save(adminUser);
				System.out.println("Admin user created with username 'admin' and password 'admin'.");
			} else {
				System.out.println("Admin user already exists.");
			}

			// Create regular user if it doesn't exist
			Optional<User> regularUserOptional = userRepository.findByUsername("user");
			if (regularUserOptional.isEmpty()) {
				User regularUser = new User();
				regularUser.setUsername("user");
				regularUser.setPassword(passwordEncoder.encode("user")); // Password is "user"
				Set<Role> roles = new HashSet<>();
				roles.add(userRole);
				regularUser.setRoles(roles);
				userRepository.save(regularUser);
				System.out.println("Regular user created with username 'user' and password 'user'.");
			} else {
				System.out.println("Regular user already exists.");
			}
		};
	}

	// REMOVE THIS @Bean ANNOTATION
	// @Bean
	// public BCryptPasswordEncoder passwordEncoder() {
	//     return new BCryptPasswordEncoder();
	// }
}