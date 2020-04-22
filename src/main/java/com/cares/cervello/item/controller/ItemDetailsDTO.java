package com.cares.cervello.item.controller;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemDetailsDTO {

	private int itemId;

	private String itemName;

	private String itemCategory;

	private BigDecimal itemPrice;

	private String itemDescription;

}
