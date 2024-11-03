package org.example;

import java.util.List;

public interface SupervisoryRole {
    void assignTaskToMultipleEmployees(List<Employee> employees, String description);
    void viewAllTasks();
}
