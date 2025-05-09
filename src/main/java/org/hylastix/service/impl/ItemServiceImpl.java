package org.hylastix.service.impl;

import lombok.RequiredArgsConstructor;
import org.hylastix.dto.ItemDTO;
import org.hylastix.mapper.ItemMapper;
import org.hylastix.model.Item;
import org.hylastix.model.User;
import org.hylastix.repository.ItemRepository;
import org.hylastix.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.hylastix.model.DeletedItem;
import org.hylastix.repository.DeletedItemRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserDetailsServiceImpl userService;
    private final DeletedItemRepository deletedItemRepository;

    @Transactional
    @Override
    public Page<ItemDTO> getAllItems(Pageable pageable, String search) {
        User currentUser = userService.getCurrentUser();

        Page<Item> itemsPage;

        if (search != null && !search.isEmpty()) {
            itemsPage = itemRepository
                    .findByUserAndItemNameContainingIgnoreCase(currentUser, search, pageable);
        } else {
            itemsPage = itemRepository.findByUser(currentUser, pageable);
        }

        return itemsPage.map(itemMapper::toDTO);
    }

    @Transactional
    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        item.setTimeStored(java.time.LocalDateTime.now());
        item.setUser(userService.getCurrentUser());

        Item savedItem = itemRepository.save(item);

        return itemMapper.toDTO(savedItem);
    }

    @Transactional
    @Override
    public ItemDTO getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        return itemMapper.toDTO(item);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        DeletedItem deleted = DeletedItem.builder()
                .itemName(item.getItemName())
                .quantity(item.getQuantity())
                .timeStored(item.getTimeStored())
                .bestBefore(item.getBestBefore())
                .deletedAt(LocalDateTime.now())
                .user(item.getUser())
                .build();

        deletedItemRepository.save(deleted);

        itemRepository.delete(item);
    }
}
