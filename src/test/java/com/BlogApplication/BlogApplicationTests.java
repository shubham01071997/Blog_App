package com.BlogApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.BlogApplication.Repository.UserRepository;

@SpringBootTest
class BlogApplicationTests {
	
	@Autowired
	private UserRepository Userrepo;

	@Test
	void contextLoads() {
	}
	@Test
	public void trepotest() {
		//String className=Userrepo.getClass().getName();
		//String packName=Userrepo.getClass().getPackage().getName();
		//System.out.println(className);
		//System.out.println(packName);
	}

}
