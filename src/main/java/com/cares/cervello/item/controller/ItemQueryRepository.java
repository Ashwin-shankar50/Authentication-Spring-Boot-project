package com.cares.cervello.item.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cares.cervello.entity.ItemDetails;

@Repository
public interface ItemQueryRepository extends JpaRepository<ItemDetails, Integer> {

	/* List<UserDetails> findByEmailId(String emailId); */

}
