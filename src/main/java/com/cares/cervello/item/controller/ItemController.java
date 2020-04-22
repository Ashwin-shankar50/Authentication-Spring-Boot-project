package com.cares.cervello.item.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	private ItemService itemService;

	@Autowired
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

	@GetMapping("/item")
	public ResponseEntity<ItemResponseDTO> itemByUserNameAndPassword() throws Exception {

		LOG.info("Entry : /item");
		ItemResponseDTO itemResponseDTO = itemService.getAllItem();
		ResponseEntity<ItemResponseDTO> response = new ResponseEntity<ItemResponseDTO>(itemResponseDTO, HttpStatus.OK);
		LOG.info("Exit : /login with {}", response);

		return response;
	}
}
