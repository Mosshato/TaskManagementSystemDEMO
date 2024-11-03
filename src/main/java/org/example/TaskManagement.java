package org.example;

public interface TaskManagement {
    void createTask(Employee employee, String description);
    void deleteTask(Employee employee, int taskId);
}
