package com.vinay.mock.user;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vinay.mock.user.User;
import com.vinay.mock.user.UserResponse;

/**
 * @author vinays
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/mockservice-servlet.xml"})
public class MockXmlUserRepositoryTest {
    
    private File file;

	@Before
	public void setUp() throws Exception{
		Resource  resource = new ClassPathResource(MockUserConstants.USERS_XML_FILE);
		file = resource.getFile();
	}
	
    @Test
    public void recordUsers() throws Exception {
	usersToXML(buildUsers());
    }
    
    @Test
    public void playUsers() throws Exception {
	UserResponse users = xmlToUsers();
	assertEquals(3, users.getUsers().size());
    }
    
    private void usersToXML(UserResponse userResponse) {
	try {
            JAXBContext context = JAXBContext.newInstance(UserResponse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(userResponse, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private UserResponse xmlToUsers() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserResponse.class);
            Unmarshaller un = context.createUnmarshaller();
            UserResponse userResponse = (UserResponse) un.unmarshal(file);
            return userResponse;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
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

}
