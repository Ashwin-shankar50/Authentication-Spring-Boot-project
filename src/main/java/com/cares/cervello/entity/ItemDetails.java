package com.cares.cervello.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "itemDetails")
@Data
public class ItemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int itemId;

	private String itemName;

	private String itemCategory;

	private BigDecimal itemPrice;

	private String itemDescription;

}