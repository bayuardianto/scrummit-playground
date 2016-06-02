package com.mitrais.scrummit.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.User;

@Service
public class UserBOImpl implements UserBO {
    @Autowired
    UserDAO userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

	@Override
	public User updateUserInfo(User user) {
		User updateUser = userDao.findByUsername(user.getUsername());
		if (updateUser != null) {
			updateUser.setEmail(user.getEmail());
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			
			if (!StringUtils.isEmpty(user.getPassword())) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String hashedPassword = encoder.encode(user.getPassword());
				updateUser.setPassword(hashedPassword);
			}
		}
		return userDao.save(updateUser);
	}

}
