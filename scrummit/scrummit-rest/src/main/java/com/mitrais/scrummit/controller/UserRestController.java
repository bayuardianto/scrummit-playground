package com.mitrais.scrummit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.model.Organization;
import com.mitrais.scrummit.model.User;

@RestController
public class UserRestController {

	@Autowired
	UserBO userBo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping(value = "/rest/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
		System.out.println("Fetching User with username " + username);
        User user = userBo.findByUsername(username);
        if (user == null) {
            System.out.println("User with username " + username + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setPassword("");
        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rest/user/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
		User user = userBo.findById(id);
        if (user == null) {
            
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setPassword("");
        
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/rest/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> updateUser(@RequestBody User user) {
		User oldUser = userBo.findByUsername(user.getUsername());
		Map<String, Object> response = new HashMap<String, Object>();
		
		if (oldUser == null ) {
			response.put("error", 1);
			response.put("message", "User not found.");
			return response;
		} 
		if (StringUtils.isEmpty(user.getNewPassword())) {
			userBo.updateUserInfo(user);
			response.put("error", 0);
			response.put("message", "User successfully updated.");
			user.setPassword("");
			response.put("user", user);
		} else if (!StringUtils.isEmpty(user.getNewPassword()) && encoder.matches(user.getPassword(), oldUser.getPassword())) {
			oldUser.setPassword(user.getNewPassword());
			userBo.updateUserInfo(oldUser);
			response.put("error", 0);
			response.put("message", "Password successfully changed.");
		} else {
			response.put("error", 1);
			response.put("message", "Old password does not match.");
		}
		
		return response;
    }
	
	@RequestMapping(value="/rest/userbyorg/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByOrgId(HttpServletRequest req) {
		//User user = (User) this.getRequest().getSession().getAttribute("CURRENT_USER");
		Organization organization = (Organization) req.getSession().getAttribute("CURRENT_ORG");
		if(organization != null)
		{
			return userBo.findByOrgId(organization.getOrganizationId());
		}
		else
		{
			return null;
		}
	}

	@RequestMapping(value = "/rest/getcurrentuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User GetCurrectUser(HttpServletRequest req){
		User user = (User) req.getSession().getAttribute("CURRENT_USER");
		return userBo.findById(String.valueOf(user.getId()));
	}
}
