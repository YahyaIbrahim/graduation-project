package com.yahya.repository;

import com.yahya.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findFirstById(Long profileId);

    User findByEmail(String email);
}
