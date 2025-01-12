package com.digilbum.app.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  @Query("select (count(u) > 0) from User u where u.email = ?1")
  boolean existsByEmail(String email);

}
