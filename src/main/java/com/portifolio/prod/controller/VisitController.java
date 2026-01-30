package com.portifolio.prod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.prod.service.VisitService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping("/visit")
    public ResponseEntity<Void> registerVisit(HttpServletRequest request) {
        visitService.registerVisit(request);
        return ResponseEntity.ok().build();
    }
}
