package org.hylastix.controller;

import lombok.RequiredArgsConstructor;
import org.hylastix.dto.DeletedItemDTO;
import org.hylastix.service.DeletedItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/deleted-items")
@RequiredArgsConstructor
public class DeletedItemController {

    private final DeletedItemService deletedItemService;

    @GetMapping
    public ResponseEntity<List<DeletedItemDTO>> getAllDeletedItems() {
        List<DeletedItemDTO> deletedItems = deletedItemService.getAllDeletedItems();
        return ResponseEntity.ok(deletedItems);
    }
}
