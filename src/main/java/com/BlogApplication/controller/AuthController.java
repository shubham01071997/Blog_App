package com.BlogApplication.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplication.Entity.User;
import com.BlogApplication.Exception.ApiException;
import com.BlogApplication.dto.JwtAuthRequest;
import com.BlogApplication.dto.UserDto;
import com.BlogApplication.security.CustomUserDetailService;
import com.BlogApplication.security.JwtTokenHelper;
import com.BlogApplication.security.jwtAuthResponse;
import com.BlogApplication.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private ModelMapper moddelMapper;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private UserService userserice;
	
	@PostMapping("/login")
	public ResponseEntity<jwtAuthResponse> createToken(@RequestBody JwtAuthRequest request ){
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails  userDetails= customUserDetailService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		jwtAuthResponse response = new jwtAuthResponse();
		response.setToken(token);
		response.setUser(moddelMapper.map((User) userDetails, UserDto.class));
		return new ResponseEntity<jwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new ApiException("Invalid username or password !!");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registeredUser = userserice.RegisterUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}
	
	
	 
}
