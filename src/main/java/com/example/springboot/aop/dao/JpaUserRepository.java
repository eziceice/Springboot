package com.example.springboot.aop.dao;

import com.example.springboot.aop.pojo.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserJpa, Long> {
}
