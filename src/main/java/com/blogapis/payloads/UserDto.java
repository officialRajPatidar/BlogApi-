package com.blogapis.payloads;

import java.util.HashSet;
import java.util.Set;

import com.blogapis.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    
    @NotEmpty(message ="Username must be at least 4 characters." )
    @Size(min = 4,  max=100, message = "Username must be at least 4 characters.")
    private String name;
    
    @Email(message = "Email address is not valid.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters.")
    private String password;
    
    @NotEmpty(message = "About must be at least 10 characters." )
    @Size(min = 10, max = 10000, message = "About must be at least 10 characters.")
    private String about;
    
    private Set<RoleDto> roles = new HashSet<>();
    
    @JsonIgnore
    public String getPassword() {
    	return this.password;
    }
    
    @JsonProperty
    public String setPassword(String password)
    {
    	return this.password= password;
    }
}
