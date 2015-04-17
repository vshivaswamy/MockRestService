package com.vinay.mock.user;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.vinay.mock.user.User;
import com.vinay.mock.user.UserResponse;

/**
 * @author vinays
 * 
 */
public class MockJsonUserRepositoryTest {

    private static final String USERS_JSON_FILE = "users.json";
    private static final String FILE_DIR = "c:/sandbox/mockService/src/main/resources/mockdata/";

    @Test
    public void recordUsers() throws Exception {
	usersToJson(buildUsers());
    }

    @Test
    public void playUsers() throws Exception {
	UserResponse users = jsonToUsers();
	assertEquals(3, users.getUsers().size());
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

    private User buildUser(String id, String firstName, String lastName,
	    String email, String phone) {
	return new User(id, firstName, lastName, email, phone);
    }

    private void usersToJson(UserResponse userResponse) throws Exception {
	new com.fasterxml.jackson.databind.ObjectMapper().writeValue(new File(
		FILE_DIR + USERS_JSON_FILE), userResponse);

    }

    private UserResponse jsonToUsers() throws Exception {
	UserResponse userResponse = null;
	userResponse = (UserResponse) new com.fasterxml.jackson.databind.ObjectMapper()
		.readValue(new File(FILE_DIR + USERS_JSON_FILE),
			UserResponse.class);
	return userResponse;
    }

}
