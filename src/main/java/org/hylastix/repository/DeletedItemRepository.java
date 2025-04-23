package org.hylastix.repository;

import org.hylastix.model.DeletedItem;
import org.hylastix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeletedItemRepository extends JpaRepository<DeletedItem, Long> {
    List<DeletedItem> findByUser(User user);
}
