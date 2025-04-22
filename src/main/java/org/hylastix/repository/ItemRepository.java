package org.hylastix.repository;


import org.hylastix.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByItemNameContainingIgnoreCase(String itemName, Pageable pageable);
}
