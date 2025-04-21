package org.hylastix.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String itemName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "time_stored")
    private LocalDateTime timeStored;

    @Column(name = "best_before")
    private LocalDate bestBefore;
}
