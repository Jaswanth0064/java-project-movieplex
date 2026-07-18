package com.movieplex.dto;

public class DashboardResponse {

    private long totalUsers;

    private long totalMovies;

    private long totalTheatres;

    private long totalBookings;

    private long totalPayments;

    private double totalRevenue;

    public DashboardResponse() {
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(long totalMovies) {
        this.totalMovies = totalMovies;
    }

    public long getTotalTheatres() {
        return totalTheatres;
    }

    public void setTotalTheatres(long totalTheatres) {
        this.totalTheatres = totalTheatres;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }

    public long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(long totalPayments) {
        this.totalPayments = totalPayments;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}