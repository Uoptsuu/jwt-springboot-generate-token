package com.spring.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class Jwt {

	public static void main(String[] args) {
		SpringApplication.run(Jwt.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));
//			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//			userService.saveUser(new User(null,"username1","matkhau123"));
//			userService.saveUser(new User(null,"vipro12345","amxamx"));
//			userService.saveUser(new User(null,"sieunhandienquang","hehehe"));
//			userService.saveUser(new User(null,"admin","1"));
//
//			userService.addToUser("username1","ROLE_USER");
//			userService.addToUser("vipro12345","ROLE_USER");
//			userService.addToUser("sieunhandienquang","ROLE_USER");
//			userService.addToUser("sieunhandienquang","ROLE_ADMIN");
//			userService.addToUser("admin","ROLE_ADMIN");
//			userService.addToUser("admin","ROLE_SUPER_ADMIN");
//		};
//	}

}
