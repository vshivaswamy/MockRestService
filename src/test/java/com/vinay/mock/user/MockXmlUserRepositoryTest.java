package com.vinay.mock.user;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.vinay.mock.user.User;
import com.vinay.mock.user.UserResponse;

/**
 * @author vinays
 *
 */
public class MockXmlUserRepositoryTest {
    
    private static final String USERS_XML_FILE = "users.xml";
    private static final String FILE_DIR = "c:/sandbox/mockService/src/main/resources/mockdata/";
    
    @Test
    public void recordUsers() throws Exception {
	usersToXML(buildUsers());
    }
    
    @Test
    public void playUsers() throws Exception {
	UserResponse users = xmlToUsers();
	assertEquals(3, users.getUsers().size());
    }
    
   private UserResponse buildUsers(){
	UserResponse userResponse = new UserResponse();
	userResponse.getUsers().add(buildUser("101", "Vin1", "Shiv1", "vin1@email.com", "123-456-7890"));
	userResponse.getUsers().add(buildUser("102", "Vin2", "Shiv2", "vin2@email.com", "223-456-7890"));
	userResponse.getUsers().add(buildUser("103", "Vin3", "Shiv3", "vin3@email.com", "323-456-7890"));
	return userResponse;
    }
    
    private User buildUser(String id, String firstName, String lastName, String email, String phone){
	return new User(id, firstName, lastName, email, phone);
    }
    
    private void usersToXML(UserResponse userResponse) {
	try {
            JAXBContext context = JAXBContext.newInstance(UserResponse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(userResponse, new File(FILE_DIR+USERS_XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private UserResponse xmlToUsers() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserResponse.class);
            Unmarshaller un = context.createUnmarshaller();
            UserResponse userResponse = (UserResponse) un.unmarshal(new File(FILE_DIR+USERS_XML_FILE));
            return userResponse;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
