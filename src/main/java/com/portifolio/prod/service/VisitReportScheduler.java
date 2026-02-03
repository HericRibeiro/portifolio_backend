package com.portifolio.prod.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portifolio.prod.model.VisitModel;
import com.portifolio.prod.repository.VisitRepository;

import org.springframework.beans.factory.annotation.Value;

@Component
public class VisitReportScheduler {
    
    @Autowired
    private VisitRepository repository;

    @Autowired
    private MailService mailService;

    @Value("${portfolio.notify.email}")
    private String notifyMail;

    @Scheduled(cron = "0 0 16 * * *")
    public void sendDailyReport() {
        System.err.println("Schedule disparou");

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);

        List<VisitModel> visits = repository.findByVisitedAtBetween(start, end);
            
        if (visits.isEmpty()) return;
        
        String body = buildEmailBody(visits);

        mailService.sendMail(
            notifyMail,
            "Relatório diário de acessos - Portfólio",
            body
        );
    }
    
    public String buildEmailBody(List<VisitModel> visits) {

        StringBuilder sb = new StringBuilder();

        sb.append("Total de acessos hoje: ")
            .append(visits.size())
            .append("\n\n");

        for (VisitModel visit : visits) {
            sb.append("IP: ").append(visit.getIp()).append("\n")
                .append("User-Agent: ").append(visit.getUserAgent()).append("\n")
                .append("Data: ").append(visit.getVisitedAt()).append("\n")
                .append("---------------------------\n");
        }

        return sb.toString();
    }

}
