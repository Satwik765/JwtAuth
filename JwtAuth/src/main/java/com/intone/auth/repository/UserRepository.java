package com.intone.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.intone.auth.model.UserRecord;
@Repository
public interface UserRepository extends CrudRepository<UserRecord, String> {
	UserRecord findByToken(String token);
}
