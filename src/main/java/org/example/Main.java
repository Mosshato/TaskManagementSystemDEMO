package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /*public static void main(String[] args) {
        System.out.println("Hello world!");

        Database.initialize();

        // Initialize employees
        Employee employee1 = new Employee(1, "John Doeeee");
        Employee employee2 = new Employee(2, "Jane Smissssth");

        // Initialize manager
        Manager mark = new Manager("Mark Johnson", List.of(employee1, employee2));

        // Create new tasks using the manager
        mark.createTask(employee1, "Prepare Q4 report"); // Should create a task
        mark.createTask(employee2, "Update client database"); // Should create another task

        // Attempt to create the same task again to check duplication handling
        mark.createTask(employee1, "Prepare Q4 report"); // This should indicate the task already exists

        // Display all tasks
        showAllTasks();
    }

    // Method to show all tasks
    public static void showAllTasks() {
        List<String> tasks = Database.getTasks(); // Retrieve tasks from the database
        System.out.println("Current Tasks:");
        for (String taskData : tasks) {
            // Skip lines that do not contain task data
            if (taskData.startsWith("[") || taskData.startsWith("#")) {
                continue; // Skip header lines and section markers
            }

            try {
                String[] parts = taskData.split("\\|");
                if (parts.length != 4) {
                    System.err.println("Task data is not in the expected format: " + taskData);
                    continue; // Skip invalid lines
                }
                //sdfsdfsdfsdfsdfd
                ///asdfsdfsdfds
            ///asdfsdfsd

                int taskId = Integer.parseInt(parts[0].trim());
                String description = parts[1].trim();
                String status = parts[2].trim();
                int assignedEmployeeId = Integer.parseInt(parts[3].trim());

                System.out.printf("Task ID: %d, Description: %s, Status: %s, Assigned to Employee ID: %d%n",
                        taskId, description, status, assignedEmployeeId);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing task data: " + taskData + " - " + e.getMessage());
            }
        }
    }
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}
