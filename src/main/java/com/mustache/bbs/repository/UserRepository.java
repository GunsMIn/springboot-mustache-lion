package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUsername(String username);

    List<User> findByEmailAddress(String emailAddress);

    Optional<User> findUserByUsername(String username);
}
