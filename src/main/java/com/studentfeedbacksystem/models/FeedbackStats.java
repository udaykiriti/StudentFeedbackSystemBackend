package com.studentfeedbacksystem.models;

public class FeedbackStats {
    private double averageTeaching;
    private double averageContent;
    private double averageMaterials;
    private double averageEngagement;
    private double averageSatisfaction;
    private int totalResponses;

    // Getters and setters
    public double getAverageTeaching() { return averageTeaching; }
    public void setAverageTeaching(double averageTeaching) { this.averageTeaching = averageTeaching; }

    public double getAverageContent() { return averageContent; }
    public void setAverageContent(double averageContent) { this.averageContent = averageContent; }

    public double getAverageMaterials() { return averageMaterials; }
    public void setAverageMaterials(double averageMaterials) { this.averageMaterials = averageMaterials; }

    public double getAverageEngagement() { return averageEngagement; }
    public void setAverageEngagement(double averageEngagement) { this.averageEngagement = averageEngagement; }

    public double getAverageSatisfaction() { return averageSatisfaction; }
    public void setAverageSatisfaction(double averageSatisfaction) { this.averageSatisfaction = averageSatisfaction; }

    public int getTotalResponses() { return totalResponses; }
    public void setTotalResponses(int totalResponses) { this.totalResponses = totalResponses; }
}