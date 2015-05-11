package com.vinay.mock.user;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author Vinays
 * 
 */
@Repository
public class MockXmlUserRepository {

	@Cacheable(value = { "usersCache" })
	public UserResponse getUsers() throws Exception {
		UserResponse userResponse = loadUsers();
		return userResponse;
	}

	private UserResponse loadUsers() throws Exception {
		JAXBContext context = JAXBContext.newInstance(UserResponse.class);
		Unmarshaller un = context.createUnmarshaller();
		Resource  resource = new ClassPathResource("mockdata/users.xml");
		return (UserResponse) un.unmarshal(resource.getFile());
	}

}
