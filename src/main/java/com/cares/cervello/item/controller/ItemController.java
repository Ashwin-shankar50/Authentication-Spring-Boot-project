package com.cares.cervello.item.controller;

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

	@GetMapping("/item")
	public ResponseEntity<ItemResponseDTO> itemByUserNameAndPassword() throws Exception {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ItemResponseDTO itemResponseDTO = itemService.getAllItem();
		if (itemResponseDTO != null) {
			status = HttpStatus.OK;
		}
		ResponseEntity<ItemResponseDTO> response = new ResponseEntity<ItemResponseDTO>(itemResponseDTO, status);
		System.out.println("Response sent");
		return response;
	}
}
