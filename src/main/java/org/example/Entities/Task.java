package org.example.Entities;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;



public class Task {
    public Task(User user) {
        this.assignee = user;
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public enum Status {
        TODO, IN_PROGRESS, IN_REVIEW, COMPLETED
    }
    private int id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
    private Status status;
    private Priority priority;
    private User assignee;

    public Task(int id, String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dueDate, Status status, Priority priority, User assignee, User reporter) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.assignee = assignee;
    }

    public Task() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", assignee=" + assignee +
                '}';
    }

    @PostConstruct
    public void init() {
        // Define the message and border symbol
        String message = "TO DO LIST MANAGER";
        char borderSymbol = '*';

        // Calculate the length for the border
        int borderLength = message.length() + 4; // 4 for padding

        // Print the top border
        printBorder(borderLength, borderSymbol);
        // Print the message with padding
        System.out.println(borderSymbol + " " + message + " " + borderSymbol);
        // Print the bottom border
        printBorder(borderLength, borderSymbol);
    }

    // Method to print the border
    private static void printBorder(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

}
