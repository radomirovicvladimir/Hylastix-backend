package org.hylastix.controller;


import lombok.RequiredArgsConstructor;
import org.hylastix.exception.ResourceNotFoundException;
import org.hylastix.model.Item;
import org.hylastix.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // create item rest api
    @PostMapping("/items")
    public Item createEmployee(@RequestBody Item item) {
        // set the timeStored to the current date and time
        item.setTimeStored(java.time.LocalDateTime.now());
        return itemRepository.save(item);
    }

    // get item by id rest api
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getEmployeeById(@PathVariable Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));
        return ResponseEntity.ok(item);
    }

    // update item rest api

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateEmployee(@PathVariable Long id, @RequestBody Item itemDetails){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));

        item.setItemName(itemDetails.getItemName());
        item.setBestBefore(itemDetails.getBestBefore());
        item.setQuantity(itemDetails.getQuantity());

        Item updatedEmployee = itemRepository.save(item);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete item rest api
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not exist with id :" + id));

        itemRepository.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
