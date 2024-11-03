package org.example;

public class Task {
    private int taskId;
    private String description;
    private String status; // E.g., "Pending", "In Progress", "Completed"
    private int assignedEmployeeId;

    public Task(int taskId, String description, String status, int assignedEmployeeId) {
        this.taskId = taskId;
        this.description = description;
        this.status = status;
        this.assignedEmployeeId = assignedEmployeeId;

        // Add task to the database file upon creation
        Database.appendData(taskId + " | " + description + " | " + status + " | " + assignedEmployeeId);
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
