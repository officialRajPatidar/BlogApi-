package com.blogapis.ctl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapis.entity.User;
import com.blogapis.exception.ApiException;
import com.blogapis.payloads.JwtAuthRequest;
import com.blogapis.payloads.UserDto;
import com.blogapis.repositories.UserRepo;
import com.blogapis.security.copy.JwtAuthResponce;
import com.blogapis.security.copy.JwtTokenHellper;
import com.blogapis.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthCtl {

    @Autowired
    private JwtTokenHellper hellper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    

    @Autowired
    private UserService service;


    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper mapper;
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponce> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        try {
            this.authenticate(request.getUsername(), request.getPassword());
            UserDetails userByUsername = this.userDetailsService.loadUserByUsername(request.getUsername());
            String token = this.hellper.generateToken(userByUsername);

         
            
            JwtAuthResponce authResponce = new JwtAuthResponce();
            authResponce.setToken(token);

            authResponce.setUser(this.mapper.map((User) userByUsername, UserDto.class));
            
            return new ResponseEntity<>(authResponce, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
           
        	UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
        
        this.authenticationManager.authenticate(authenticate);
        
        } catch (BadCredentialsException e) {
        	System.out.println("invaild Detials !!");
            throw new ApiException("Invalid username or password");
        }
    }
    
    //resiter new user api
    
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        boolean exists = userRepository.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto dto) {
        // Check if email already exists
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException("Email already exists.");
        }

        // Register new user
        UserDto registerNewUser = service.registerNewUser(dto);
        return new ResponseEntity<>(registerNewUser, HttpStatus.CREATED);
    }

    

}
