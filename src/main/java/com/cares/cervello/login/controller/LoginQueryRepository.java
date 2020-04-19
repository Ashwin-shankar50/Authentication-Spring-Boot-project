package com.cares.cervello.login.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cares.cervello.entity.UserDetails;

@Repository
public interface LoginQueryRepository extends JpaRepository<UserDetails, Integer> {

	List<UserDetails> findByEmailIdAndPassword(String emailId, String password);

}
