package com.BlogApplication.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplication.dto.AprResponse;
import com.BlogApplication.dto.UserDto;
import com.BlogApplication.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userserice;
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto){
		
		UserDto create=userserice.createUser(userdto);
		return new ResponseEntity<>(create,HttpStatus.CREATED);	
	}

	@PutMapping("/update/{userid}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable Integer userid){
		
		UserDto update=userserice.updateUser(userdto,userid);
		return ResponseEntity.ok(update);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<AprResponse> deleteUser(@PathVariable Integer userid){
		userserice.deleteById(userid);
		return new ResponseEntity<AprResponse>(new AprResponse("user delete successfully",true),HttpStatus.OK);	
	}

	@GetMapping("{userid}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userid){
		return ResponseEntity.ok(userserice.getUserById(userid));
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(userserice.getAllUser());
	}
}
//localhost:8080/api/user/getAllUser
//localhost:8080/api/user/delete/2
//localhost:8080/api/user/update/2
//localhost:8080/api/user/add
//localhost:8080/api/user/1
//
