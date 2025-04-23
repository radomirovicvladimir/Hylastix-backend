package org.hylastix.service.impl;

import lombok.RequiredArgsConstructor;
import org.hylastix.dto.DeletedItemDTO;
import org.hylastix.mapper.DeletedItemMapper;
import org.hylastix.model.User;
import org.hylastix.repository.DeletedItemRepository;
import org.hylastix.service.DeletedItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeletedItemServiceImpl implements DeletedItemService {

    private final DeletedItemRepository deletedItemRepository;
    private final DeletedItemMapper deletedItemMapper;
    private final UserDetailsServiceImpl userService;

    @Override
    public List<DeletedItemDTO> getAllDeletedItems() {
        User currentUser = userService.getCurrentUser();

        return deletedItemRepository.findByUser(currentUser).stream()
                .map(deletedItemMapper::toDTO)
                .toList();
    }
}
