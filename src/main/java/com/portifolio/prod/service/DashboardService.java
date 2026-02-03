package com.portifolio.prod.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.portifolio.prod.dto.DashboardResponse;
import com.portifolio.prod.dto.VisitResponse;
import com.portifolio.prod.repository.VisitRepository;

import java.util.List;

@Service
public class DashboardService {

    private final VisitRepository visitRepository;

    public DashboardService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public DashboardResponse getDashboardData() {

        long totalVisits = visitRepository.count();

        long uniqueVisitsToday =
            visitRepository.countByIpAndVisitedAtAfter(
                "172.19.0.1",
                LocalDate.now().atStartOfDay()
            );

        List<VisitResponse> visits = visitRepository.findAll()
            .stream()
            .map(v -> new VisitResponse(
                v.getIp(),
                v.getCountry(),
                v.getCity(),
                v.getUserAgent(),
                v.getVisitedAt()
            ))
            .toList();

        return new DashboardResponse(
            totalVisits,
            uniqueVisitsToday,
            visits
        );
    }
}

