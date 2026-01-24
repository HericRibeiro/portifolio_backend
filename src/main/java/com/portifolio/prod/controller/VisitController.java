package com.portifolio.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.prod.service.MailService;

@RestController
@RequestMapping("/user")
public class VisitController {

    @Value("${portfolio.notify.email}")
    private String notifyMail;

    @Autowired
    private MailService mailService;

    @GetMapping("/visit")
    public ResponseEntity<String> registerVisit() {
        mailService.sendMail(
            notifyMail,
            "New access to the portfolio",
            "Someone just accessed your portfolio!"
        );

        return ResponseEntity.ok("Visit registered");
    }
}
