package com.vinay.mock.user;

import java.io.File;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


/**
 * @author Vinays
 * 
 */
@Repository
public class MockJsonUserRepository {

    private static final String USERS_JSON_FILE = "users.json";
    private static final String FILE_DIR = "c:/sandbox/mockService/src/main/resources/mockdata/";

    @Cacheable(value = { "usersCache" })
    public UserResponse getUsers() throws Exception {
	UserResponse userResponse = loadUsers();
	return userResponse;
    }

    private UserResponse loadUsers() throws Exception {
	return (UserResponse) new com.fasterxml.jackson.databind.ObjectMapper()
		.readValue(new File(FILE_DIR + USERS_JSON_FILE),
			UserResponse.class);
    }
}
