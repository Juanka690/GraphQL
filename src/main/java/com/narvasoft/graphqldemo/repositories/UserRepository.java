package com.narvasoft.graphqldemo.repositories;

import com.narvasoft.graphqldemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
}
