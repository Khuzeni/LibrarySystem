package za.ac.cput.service;

import java.util.Map;

public interface IDashboard {
    Map<String, Object> getDashboardStats();
    Map<String, Object> getRecentLoans();
    Map<String, Object> getRecentlyAddedBooks();
    Map<String, Object> getMostBorrowedBooks();
    Map<String, Object> getTopMembers();
    Map<String, Object> getMonthlyStatistics();
}
