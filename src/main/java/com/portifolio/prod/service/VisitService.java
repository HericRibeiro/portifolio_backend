package com.portifolio.prod.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.prod.model.VisitModel;
import com.portifolio.prod.repository.VisitRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class VisitService {
    
    @Autowired
    private VisitRepository repository;

    public void registerVisit(HttpServletRequest request) {
        String ip = getClientIp(request);

        long hits = repository.countByIpAndVisitedAtAfter(
            ip,
            LocalDateTime.now().minusMinutes(1)
        );
        if (hits > 20) return;

        VisitModel visit = new VisitModel();
        visit.setIp(ip);
        visit.setUserAgent(request.getHeader("User-Agent"));
        visit.setVisitedAt(LocalDateTime.now());

        repository.save(visit);
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Fowarded-For");

        return xfHeader == null
            ? request.getRemoteAddr()
            : xfHeader.split(",")[0];
    }
}