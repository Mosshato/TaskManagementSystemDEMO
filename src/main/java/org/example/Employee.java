package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee {
    private int employeeId;
    private String name;
    private List<Task> tasks;

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    // Adding getId() method
    public int getId() {
        return employeeId; // Return the employee's ID
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Return a read-only view of the tasks
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    // Method to get the count of tasks assigned to this employee
    public int getTaskCount() {
        return tasks.size();
    }

    // Optional: Method to clear all tasks (if needed)
    public void clearTasks() {
        tasks.clear();
    }
}
