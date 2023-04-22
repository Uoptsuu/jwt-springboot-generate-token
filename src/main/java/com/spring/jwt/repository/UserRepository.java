package com.spring.jwt.repository;

import com.spring.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE is_delete = false", nativeQuery = true)
    List<User> findAll();
    @Query(value = "SELECT * FROM users WHERE is_delete = false AND is_active = true AND username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);
    User save(User user);

}
