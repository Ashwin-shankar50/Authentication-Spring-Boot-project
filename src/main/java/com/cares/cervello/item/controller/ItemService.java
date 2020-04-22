package com.cares.cervello.item.controller;

import com.cares.cervello.exception.DatabaseAccessException;

public interface ItemService {

	ItemResponseDTO getAllItem() throws DatabaseAccessException;
}
