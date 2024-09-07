package com.blogapis;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogapis.config.AppConstents;
import com.blogapis.entity.Role;
import com.blogapis.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApiaApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiaApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return  new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role role= new Role();
			role.setId(AppConstents.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			
			Role role1= new Role();
			role1.setId(AppConstents.NORMAL_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role> of = List.of(role,role1);
			
			List<Role> saveAll = this.repo.saveAll(of);
			
			saveAll.forEach(r->{System.out.println(r.getName());
			}
			);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(this.encoder.encode("xyz"));
		
	}

}
