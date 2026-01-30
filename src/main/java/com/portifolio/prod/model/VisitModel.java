package com.portifolio.prod.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Visits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String country;

    private String city;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    private LocalDateTime visitedAt;
}
