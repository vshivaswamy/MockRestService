package com.vinay.mock.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vinay.mock.user.User;
import com.vinay.mock.user.UserService;
import com.vinay.mock.user.UserResponse;

/**
 * @author Vinays
 * 
 */
@RestController
@RequestMapping("/1.0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, headers = { "Accept=application/json,application/xml" }, produces = {
	    "application/json", "application/xml" })
    @ResponseBody
    public UserResponse getUsers(HttpServletRequest request) throws Exception {
	UserResponse users = userService.getUsers();

	return users;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = { "Accept=application/json,application/xml" }, produces = {
	    "application/json", "application/xml" })
    @ResponseBody
    public User getUserById(@PathVariable("id") String id,
	    HttpServletRequest request) throws Exception {
	User user = userService.getUserById(id);

	return user;
    }

    @RequestMapping(method = RequestMethod.POST, headers = { "Accept=application/json,application/xml" }, produces = {
	    "application/json", "application/xml" } )
    @ResponseBody
    public User addUser(@RequestBody User user, HttpServletRequest request)
	    throws Exception {
	if (user == null)
	    return null;
	userService.addUser(user);
	return userService.getUserById(user.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, headers = { "Accept=application/json,application/xml" }, produces = {
	    "application/json", "application/xml" })
    @ResponseBody
    public User updateUser(@RequestBody User user, @PathVariable String id, HttpServletRequest request)
	    throws Exception {
	if (user == null)
	    return null;
	userService.updateUser(id, user);
	return userService.getUserById(user.getId());
    }
}
