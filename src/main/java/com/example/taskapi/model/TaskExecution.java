package com.example.taskapi.model;

import java.time.LocalDateTime;

public class TaskExecution {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String output;
    private String error;

    // Default constructor
    public TaskExecution() {}

    // All-args constructor
    public TaskExecution(LocalDateTime startTime, LocalDateTime endTime, String output, String error) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.output = output;
        this.error = error;
    }

    // Getters & Setters
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getOutput() { return output; }
    public void setOutput(String output) { this.output = output; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}