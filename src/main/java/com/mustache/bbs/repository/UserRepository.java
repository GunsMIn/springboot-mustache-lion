package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User>findByUsername(String username);
}
