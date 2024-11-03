package org.example;

import java.util.List;

public class BigBoss implements TaskManagement, SupervisoryRole{
    private String name;
    private List<Manager> managers;

    public BigBoss(String name, List<Manager> managers) {
        this.name = name;
        this.managers = managers;
    }


    @Override
    public void createTask(Employee employee, String description) {
        int taskId = IDGenerator.getNewTaskId();
        Task newTask = new Task(taskId, description, "Pending", employee.getEmployeeId());

        // Format the task data for saving
        String taskData = taskId + " | " + description + " | Pending | " + employee.getEmployeeId();

        // Check if the task already exists in the database before appending
        if (!Database.taskExists(taskData)) {
            employee.addTask(newTask);
            Database.appendData(taskData); // Save the task to the db file
            System.out.println("Task created for " + employee.getName());
        } else {
            System.out.println("Task already exists: " + taskData);
        }
    }


    @Override
    public void deleteTask(Employee employee, int taskId) {
        employee.removeTask(taskId);
        System.out.println("Task removed from " + employee.getName());
    }

    @Override
    public void assignTaskToMultipleEmployees(List<Employee> employees, String description) {
        for (Employee employee : employees) {
            createTask(employee, description);
        }
    }

    @Override
    public void viewAllTasks() {
        managers.forEach(manager -> manager.viewAllTasks());
    }

    public void createTaskForManager(Manager manager, String description) {
        System.out.println("BigBoss created a task for manager " + manager.getName());
    }

    public void deleteTaskForManager(Manager manager, int taskId) {
        System.out.println("BigBoss removed a task from manager " + manager.getName());
    }
}
