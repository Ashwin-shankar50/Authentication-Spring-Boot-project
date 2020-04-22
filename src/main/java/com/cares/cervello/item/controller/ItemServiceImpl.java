package com.cares.cervello.item.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.cervello.entity.ItemDetails;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemQueryRepository itemQueryRepository;

	@Override
	public ItemResponseDTO getAllItem() throws Exception {
		System.out.println("Fetching all items from item_details table");
		try {
			List<ItemDetails> itemList = getItemDetails();
			ItemResponseDTO itemResponseDTO = new ItemResponseDTO();
			// Optional.ofNullable(itemList).ifPresent(items -> items.stream().map(items));
			int numberOfItems = itemList.size();
			List<ItemDetailsDTO> itemDetails = new ArrayList<ItemDetailsDTO>();
			if (numberOfItems > 0) {
				for (int i = 0; i < numberOfItems; i++) {
					itemDetails.add(getItemFromItemDetailsEntity(itemList.get(i)));
				}
			}
			itemResponseDTO.setItemList(itemDetails);
			return itemResponseDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ItemDetails> getItemDetails() throws Exception {
		List<ItemDetails> itemList = new ArrayList<ItemDetails>();
		try {
			System.out.println("========(())=========");
			itemList = itemQueryRepository.findAll();
			System.out.println("=================");
			return itemList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public ItemDetailsDTO getItemFromItemDetailsEntity(ItemDetails itemDetails) {
		ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
		itemDetailsDTO.setItemId(Optional.ofNullable(itemDetails.getItemId()).orElse(null));
		itemDetailsDTO.setItemName(Optional.ofNullable(itemDetails.getItemName()).orElse(null));
		itemDetailsDTO.setItemPrice(Optional.ofNullable((itemDetails.getItemPrice())).orElse(null));
		itemDetailsDTO.setItemCategory(Optional.ofNullable(itemDetails.getItemCategory()).orElse(null));
		itemDetailsDTO.setItemDescription(Optional.ofNullable(itemDetails.getItemDescription()).orElse(null));
		return itemDetailsDTO;
	}

}
