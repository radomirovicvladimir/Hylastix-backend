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
public class DeletedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer quantity;
    private LocalDateTime timeStored;
    private LocalDate bestBefore;
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

