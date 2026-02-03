package com.portifolio.prod.dto;

import java.util.List;

public class DashboardResponse {

    private long totalVisits;
    private long uniqueVisitsToday;
    private List<VisitResponse> visits;

    public DashboardResponse(long totalVisits, long uniqueVisitsToday, List<VisitResponse> visits) {
        this.totalVisits = totalVisits;
        this.uniqueVisitsToday = uniqueVisitsToday;
        this.visits = visits;
    }

    public long getTotalVisits() {
        return totalVisits;
    }

    public long getUniqueVisitsToday() {
        return uniqueVisitsToday;
    }

    public List<VisitResponse> getVisits() {
        return visits;
    }
}
