package com.studies.microserviceuser.repository;

import com.studies.microserviceuser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
