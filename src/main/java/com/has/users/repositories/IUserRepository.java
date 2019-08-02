package com.has.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.has.users.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
