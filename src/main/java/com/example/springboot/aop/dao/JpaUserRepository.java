//package com.example.springboot.aop.dao;
//
//import com.example.springboot.aop.pojo.UserJpa;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface JpaUserRepository extends JpaRepository<UserJpa, Long> {
//
//    @Query("from user where user_name like concat('%', ?1, '%') and note like concat('%', ?2, '%') ")
//    List<UserJpa> findUsers(String userName, String note);
//
//    List<UserJpa> findByUsernameLike(String userName);
//
//    List<UserJpa> findByUsernamelikeOrNoteLike(String userName, String note);
//
//    UserJpa getUserJpaById(Long id);
//}
