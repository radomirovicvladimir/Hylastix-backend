package org.hylastix.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DeletedItemDTO(Long id,
                             String itemName,
                             Integer quantity,
                             LocalDateTime timeStored,
                             LocalDate bestBefore) {
}
