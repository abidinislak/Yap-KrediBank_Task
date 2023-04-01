package com.YapiKredi.YapiKredi.repository;

import com.YapiKredi.YapiKredi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
}
