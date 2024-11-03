package org.example;

import java.util.List;

public class Manager implements TaskManagement, SupervisoryRole{

        private String name;
        private List<Employee> employees;

        public Manager(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
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
            for (Employee employee : employees) {
                System.out.println("Tasks for " + employee.getName() + ":");
                employee.getTasks().forEach(System.out::println);
            }
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
