package com.taskify.taskify.repository;

import com.taskify.taskify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
