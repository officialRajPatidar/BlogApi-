package com.blogapis.security.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapis.entity.User;
import com.blogapis.exception.ResourceNotFoundException;
import com.blogapis.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from db by user anme
		
		
		User user = this.repo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email :"+username, 0));
		
		return user;
	}

}
