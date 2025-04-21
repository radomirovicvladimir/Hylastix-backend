package org.hylastix.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private Long id;
    private String itemName;
    private Integer quantity;
    private LocalDateTime timeStored;
    private LocalDate bestBefore;
}
