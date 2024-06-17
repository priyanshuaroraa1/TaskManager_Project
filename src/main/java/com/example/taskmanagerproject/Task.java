package com.example.taskmanagerproject;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private String description;
    private LocalDate dueDate;
    private int priority;

    public Task(String description, LocalDate dueDate, int priority) {
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task other) {
        // First compare by due date
        int dateComparison = this.dueDate.compareTo(other.dueDate);
        if (dateComparison != 0) {
            return dateComparison;
        }
        // If due dates are equal, compare by priority
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                '}';
    }
}