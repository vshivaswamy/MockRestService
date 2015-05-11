package com.vinay.mock.user;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vinays
 * 
 */

 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(locations = {"classpath*:**/mockservice-servlet.xml"})
 public class MockJsonUserRepositoryTest {

	
	private File file;

	@Before
	public void setUp() throws Exception{
		Resource  resource = new ClassPathResource(MockUserConstants.USERS_JSON_FILE);
		file = resource.getFile();
	}

	@Test
	public void recordUsers() throws Exception {
		usersToJson(buildUsers());
	}

	@Test
	public void playUsers() throws Exception {
		UserResponse users = jsonToUsers();
		assertEquals(3, users.getUsers().size());
	}

	private User buildUser(String id, String firstName, String lastName,
			String email, String phone) {
		return new User(id, firstName, lastName, email, phone);
	}

	private void usersToJson(UserResponse userResponse) throws Exception {
		new com.fasterxml.jackson.databind.ObjectMapper().writeValue(file, userResponse);
	}

	private UserResponse jsonToUsers() throws Exception {
		UserResponse userResponse = null;
		userResponse = (UserResponse) new com.fasterxml.jackson.databind.ObjectMapper()
				.readValue(file,
						UserResponse.class);
		return userResponse;
	}

	private UserResponse buildUsers() {
		UserResponse userResponse = new UserResponse();
		userResponse.getUsers().add(
				buildUser("101", "Vin1", "Shiv1", "vin1@email.com",
						"123-456-7890"));
		userResponse.getUsers().add(
				buildUser("102", "Vin2", "Shiv2", "vin2@email.com",
						"223-456-7890"));
		userResponse.getUsers().add(
				buildUser("103", "Vin3", "Shiv3", "vin3@email.com",
						"323-456-7890"));
		return userResponse;
	}

}
