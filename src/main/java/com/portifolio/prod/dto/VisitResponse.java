package com.portifolio.prod.dto;

import java.time.LocalDateTime;

public class VisitResponse {

    private String ip;
    private String country;
    private String city;
    private String userAgent;
    private LocalDateTime visitedAt;

    public VisitResponse(
        String ip,
        String country,
        String city,
        String userAgent,
        LocalDateTime visitedAt
    ) {
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.userAgent = userAgent;
        this.visitedAt = visitedAt;
    }

    public String getIp() {
        return ip;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getVisitedAt() {
        return visitedAt;
    }
}
