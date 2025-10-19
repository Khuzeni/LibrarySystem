package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.service.DashboardService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        System.out.println("GET /api/dashboard/stats called");
        return dashboardService.getDashboardStats();
    }

    @GetMapping("/recent-loans")
    public Map<String, Object> getRecentLoans() {
        System.out.println("GET /api/dashboard/recent-loans called");
        return dashboardService.getRecentLoans();
    }

    @GetMapping("/recent-books")
    public Map<String, Object> getRecentlyAddedBooks() {
        System.out.println("GET /api/dashboard/recent-books called");
        return dashboardService.getRecentlyAddedBooks();
    }

    @GetMapping("/most-borrowed")
    public Map<String, Object> getMostBorrowedBooks() {
        System.out.println("GET /api/dashboard/most-borrowed called");
        return dashboardService.getMostBorrowedBooks();
    }

    @GetMapping("/top-members")
    public Map<String, Object> getTopMembers() {
        System.out.println("GET /api/dashboard/top-members called");
        return dashboardService.getTopMembers();
    }

    @GetMapping("/monthly-stats")
    public Map<String, Object> getMonthlyStatistics() {
        System.out.println("GET /api/dashboard/monthly-stats called");
        return dashboardService.getMonthlyStatistics();
    }
}
