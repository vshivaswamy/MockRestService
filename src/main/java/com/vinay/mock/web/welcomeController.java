package com.vinay.mock.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vinay.mock.user.UserService;
import com.vinay.mock.user.UserResponse;


/**
 * @author Vinays
 * 
 */
@Controller
public class welcomeController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/welcome" ,method = RequestMethod.GET)
    public ModelAndView hello(
	    @RequestParam(value = "name", required = false, defaultValue = "xyz.....") String name,
	    HttpServletRequest request) throws Exception {
	UserResponse users = userService.getUsers();
	return new ModelAndView("welcome","users", users.getUsers());
    }
}
