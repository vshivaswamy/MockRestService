package com.vinay.mock.user;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


/**
 * @author Vinays
 * 
 */
@Repository
public class MockXmlUserRepository {

    private static final String USERS_XML_FILE = "users.xml";
    private static final String FILE_DIR = "c:/sandbox/mockService/src/main/resources/mockdata/";
    
    @Cacheable(value = { "usersCache" })
    public UserResponse getUsers() throws Exception {
	UserResponse userResponse = loadUsers();
	return userResponse;
    }

    private UserResponse loadUsers() throws Exception {
	JAXBContext context = JAXBContext.newInstance(UserResponse.class);
	Unmarshaller un = context.createUnmarshaller();
	return (UserResponse) un.unmarshal(new File(FILE_DIR + USERS_XML_FILE));
    }

}
