package org.hylastix.repository;


import org.hylastix.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.hylastix.model.User;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByUser(User user, Pageable pageable);
    Page<Item> findByUserAndItemNameContainingIgnoreCase(User user, String itemName, Pageable pageable);
}
