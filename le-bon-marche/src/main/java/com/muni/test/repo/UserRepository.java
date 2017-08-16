package com.muni.test.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.muni.test.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByUsername(String username);
}
