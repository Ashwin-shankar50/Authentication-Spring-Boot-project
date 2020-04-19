package com.cares.cervello.registration.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cares.cervello.entity.UserDetails;

@Repository
public interface RegistrationQueryRepository extends JpaRepository<UserDetails, Integer> {
	
	List<UserDetails> findByEmailId(String emailId);
}
