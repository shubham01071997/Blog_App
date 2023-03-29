package com.BlogApplication.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BlogApplication.Entity.Role;
import com.BlogApplication.Entity.User;
import com.BlogApplication.Exception.ResourceNotFoundException;
import com.BlogApplication.Repository.RoleRepository;
import com.BlogApplication.Repository.UserRepository;
import com.BlogApplication.config.AppConstant;
import com.BlogApplication.dto.UserDto;
import com.BlogApplication.services.UserService;
@Service
public class userServicImpl implements UserService {
     @Autowired
	 private UserRepository userrepo;
     @Autowired
     private ModelMapper moddelMapper;
     
     @Autowired
	 private PasswordEncoder PasswordEncoder;
     
     @Autowired
     private RoleRepository  Rolerepository;
     
	@Override
	public UserDto createUser(UserDto user) {
		User u=UserDtoToUser(user);
		User save = userrepo.save(u);
		return  UserToUserDto(save);
	}

	@Override
	public UserDto updateUser(UserDto user,Integer userid) {
		User u= userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","Id",userid));
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setAbout(user.getAbout());
		User save = userrepo.save(u);
		UserDto userDto1=UserToUserDto(save);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return  UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> findAll = userrepo.findAll();
		List<UserDto> collect = findAll.stream().map(user->UserToUserDto(user)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public void deleteById(Integer userId) {
		User user=userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		userrepo.delete(user);
	}
	private User UserDtoToUser(UserDto user) {
		User u=moddelMapper.map(user,User.class);
//		User u=new User();
//		u.setId(user.getId());
//		u.setName(user.getName());
//		u.setAbout(user.getAbout());
//		u.setEmail(user.getEmail());
//		u.setPassword(user.getPassword());
		return u;
	}
	
	private UserDto UserToUserDto(User user) {
		UserDto u=moddelMapper.map(user,UserDto.class);
//		UserDto u=new UserDto();
//		u.setId(user.getId());
//		u.setName(user.getName());
//		u.setAbout(user.getAbout());
//		u.setEmail(user.getEmail());
//		u.setPassword(user.getPassword());
		return u;
	}

	@Override
	public UserDto RegisterUser(UserDto user) {
		User map = moddelMapper.map(user,User.class);
		map.setPassword(PasswordEncoder.encode(map.getPassword()));
		Role role=null;
		if(user.getRole().equals("USER")){
		     role = Rolerepository.findById(AppConstant.ROLE_USER).get();
		}else {
			 role = Rolerepository.findById(AppConstant.ROLE_ADMIN).get();
		}
		map.getRoles().add(role);
		User save = userrepo.save(map);
		return  UserToUserDto(save);
	}

}
