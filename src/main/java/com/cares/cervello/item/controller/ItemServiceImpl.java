package com.cares.cervello.item.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.ItemDetails;
import com.cares.cervello.exception.DatabaseAccessException;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemQueryRepository itemQueryRepository;
	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Override
	public ItemResponseDTO getAllItem() throws DatabaseAccessException {

		LOG.info("getAllItem");
		List<ItemDetails> itemList = getItemDetails();
		ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
		List<ItemDetailsDTO> itemDetails = new ArrayList<ItemDetailsDTO>();
		Optional.ofNullable(itemList).ifPresent(
				item -> item.stream().map(ItemServiceImpl::getItemFromItemDetailsEntity).forEach(itemDetails::add));
		itemResponseDTO.setItemList(itemDetails);
		return itemResponseDTO;
	}

	public List<ItemDetails> getItemDetails() throws DatabaseAccessException {

		LOG.info("getItemDetails");
		List<ItemDetails> itemList = new ArrayList<ItemDetails>();
		try {
			itemList = itemQueryRepository.findAll();
			return itemList;
		} catch (Exception e) {
			LOG.error("Exception : {}", e);
			throw new DatabaseAccessException("Database connection failed");
		}
	}

	public static ItemDetailsDTO getItemFromItemDetailsEntity(ItemDetails itemDetails) {

		ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
		itemDetailsDTO.setItemId(Optional.ofNullable(itemDetails.getItemId()).orElse(null));
		itemDetailsDTO.setItemName(Optional.ofNullable(itemDetails.getItemName()).orElse(null));
		itemDetailsDTO.setItemPrice(
				Optional.ofNullable(new BigDecimal((itemDetails.getItemPrice()).toString())).orElse(null));
		itemDetailsDTO.setItemCategory(Optional.ofNullable(itemDetails.getItemCategory()).orElse(null));
		itemDetailsDTO.setItemDescription(Optional.ofNullable(itemDetails.getItemDescription()).orElse(null));

		return itemDetailsDTO;
	}

}
