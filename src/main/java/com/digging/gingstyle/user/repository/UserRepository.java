package com.digging.gingstyle.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
