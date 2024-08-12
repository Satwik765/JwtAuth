package com.intone.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intone.auth.model.UserRecord;
import com.intone.auth.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;
public void addUserAndToken(UserRecord userRecord) {
	userRepository.save(userRecord);
}
public Optional<UserRecord> getUserRecord(String userId) {
	return userRepository.findById(userId);
}
public UserRecord getUserRecordByToken(String token) {
	return userRepository.findByToken(token);
}
}
