package com.portifolio.prod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portifolio.prod.model.VisitModel;
import java.time.LocalDateTime;


public interface VisitRepository extends JpaRepository<VisitModel, Long>{

    List<VisitModel> findByVisitedAtBetween(
        LocalDateTime start,
        LocalDateTime end
    );

    long countByIpAndVisitedAtAfter(
        String ip,
        LocalDateTime after
    );
}