package com.example.demo.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUserRepository extends JpaRepository<UserEntity, Integer> {

}
