package com.mitrais.scrummit.bo.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.User;

@Service
public class UserBOImpl extends BaseBOImpl<User, UserDAO> implements UserBO {

    public UserBOImpl() {
        super(true);
    }

    @Override
    public User findByUsername(String username) {
        return currentDAO.findByUsername(username);
    }

    @Override
    public User findByActivationKey(String activationKey) {
        return currentDAO.findByActivationKey(activationKey);
    }

    @Override
    public User activateUser(String username) {
        User updateUser = currentDAO.findByUsername(username);
        if (updateUser != null) {
            updateUser.setActivationKey(null);
            updateUser.setIsActivated(true);
        }
        return save(updateUser);
    }

	@Override
	public User updateUserInfo(User user) {
        User updateUser = currentDAO.findByUsername(user.getUsername());
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
        return save(updateUser);
	}

	@Override
	public User findById(String id) {
		
		return currentDAO.findOne(id);
	}

}
