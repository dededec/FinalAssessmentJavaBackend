package com.labestia.finalassessmentjavabackend.repository;

import com.labestia.finalassessmentjavabackend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
