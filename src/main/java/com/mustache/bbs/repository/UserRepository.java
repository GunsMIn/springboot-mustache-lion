package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
