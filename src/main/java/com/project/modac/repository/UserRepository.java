package com.project.modac.repository;

import com.project.modac.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOptionByUsername(String username);

    User findByUsername(String username);

}
