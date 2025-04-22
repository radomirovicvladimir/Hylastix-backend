package org.hylastix.service.impl;

import lombok.RequiredArgsConstructor;
import org.hylastix.dto.ItemDTO;
import org.hylastix.mapper.ItemMapper;
import org.hylastix.model.Item;
import org.hylastix.repository.ItemRepository;
import org.hylastix.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Page<ItemDTO> getAllItems(Pageable pageable) {
        Page<Item> itemsPage = itemRepository.findAll(pageable);

        return itemsPage.map(itemMapper::toDTO);
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        item.setTimeStored(java.time.LocalDateTime.now());

        Item savedItem = itemRepository.save(item);

        return itemMapper.toDTO(savedItem);
    }

    @Override
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        return itemMapper.toDTO(item);
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setItemName(itemDTO.getItemName());
        item.setQuantity(itemDTO.getQuantity());
        item.setBestBefore(itemDTO.getBestBefore());

        Item updatedItem = itemRepository.save(item);

        return itemMapper.toDTO(updatedItem);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        itemRepository.delete(item);
    }
}
