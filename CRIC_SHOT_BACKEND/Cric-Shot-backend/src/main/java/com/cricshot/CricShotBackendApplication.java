package com.cricshot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cricshot.config.AppConstants;
import com.cricshot.entities.Role;
import com.cricshot.repositories.RoleRepository;

@SpringBootApplication
public class CricShotBackendApplication implements CommandLineRunner {
    @Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
	public static void main(String[] args) {
		SpringApplication.run(CricShotBackendApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.passwordEncoder.encode("xyz"));
		try {
			Role role1=new Role();
			role1.setId(AppConstants.ADMIN_USER);
			role1.setName("ADMIN_USER");
			
			Role role2=new Role();
			role2.setId(AppConstants.NORMAL_USER);
			role2.setName("NORMAL_USER");
			
			List<Role> roles=List.of(role1,role2);
			List<Role> result=this.roleRepository.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
