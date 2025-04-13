package com.exemplo.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    @Query("SELECT COALESCE(MAX(id), 0) FROM AdminUser")
    Integer findMaxId();

    @Query(value = "SELECT * FROM dbo.admin_users WHERE username = ?1", nativeQuery = true)
    Optional<AdminUser> findByUsername(String username);
}