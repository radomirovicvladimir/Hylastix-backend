package org.hylastix.service;

import org.hylastix.dto.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    Page<ItemDTO> getAllItems(Pageable pageable, String search);

    ItemDTO createItem(ItemDTO itemDTO);

    ItemDTO getItemById(Long id);

    ItemDTO updateItem(Long id, ItemDTO itemDTO);

    void deleteItem(Long id);
}
