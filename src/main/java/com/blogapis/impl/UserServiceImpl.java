package com.blogapis.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.blogapis.exception.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapis.config.AppConstents;
import com.blogapis.entity.Role;
import com.blogapis.entity.User;
import com.blogapis.payloads.UserDto;
import com.blogapis.repositories.RoleRepo;
import com.blogapis.repositories.UserRepo;
import com.blogapis.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo repo;
	
	
	@Override
	public UserDto createUser(UserDto userdto) {
		User user = this.dtoToUser(userdto);
	User saveUser=	this.userrepo.save(user);
	
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		
		User user= this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User updateduser =this.userrepo.save(user);
	UserDto userdto1=	this.userToDto(updateduser);
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user= this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
List< User> users = this.userrepo.findAll();
 List<UserDto> userDto =users.stream().map(user->userToDto(user)).collect(Collectors.toList());



		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user= this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
   
		this.userrepo.delete(user);
	}
	
	private User dtoToUser(UserDto userdto) {
		
		User user = this.modelMapper.map(userdto, User.class);
		
//		user.setId(userdto.getId());
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		
		return user;
		
	}
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
		
		return userDto;
	}


    @Override
    public UserDto registerNewUser(UserDto userdto) {
        // Check if email already exists
        if (userrepo.existsByEmail(userdto.getEmail())) {
            throw new ApiException("Email already exists.");
        }

        User user = this.dtoToUser(userdto);

        // Encode the password
        user.setPassword(this.encoder.encode(user.getPassword()));

        // Set roles
        Role role = this.repo.findById(AppConstents.NORMAL_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", AppConstents.NORMAL_USER));
        user.getRoles().add(role);

        User savedUser = this.userrepo.save(user);
        return this.userToDto(savedUser);
    }

}
