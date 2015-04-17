package com.vinay.mock.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vinays
 *
 */
@XmlRootElement(name = "Users")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserResponse implements Serializable{
    private static final long serialVersionUID = -1153674828263951969L;
    @XmlElement(name = "User")
    private List<User> users = new ArrayList<User>();
    
    public UserResponse(List<User> users) {
	this.users = users;
    }
    
    public UserResponse() {
    }
    
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
