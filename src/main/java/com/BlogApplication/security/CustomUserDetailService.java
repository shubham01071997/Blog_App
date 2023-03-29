package com.BlogApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BlogApplication.Entity.User;
import com.BlogApplication.Exception.ResourceNotFoundExceptionNew;
import com.BlogApplication.Repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService{
	
	  @Autowired
	  private UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundExceptionNew("User","Id",username));
		return user;
	}

}
