package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Manager extends User implements Login, Task{
    List<Task> tasks;
    Boolean isLoggedIn;

    Manager(String name) {
        super(name);
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public void login() {
        System.out.println("You are logged in as a Manager");
        this.isLoggedIn = true;
    }

    @Override
    public void logout() {
        System.out.println("You logged out from a Manager user");
    }

    @Override
    public Boolean isloggedin() {
        return this.isLoggedIn;
    }

    @Override
    public void viewTasks() {
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";

        try {
            System.out.println("Your tasks for today are:");
            StringBuilder jsonBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                if(person.getString("name").equalsIgnoreCase(this.getName())) {
                    JSONArray tasks = person.getJSONArray("tasks");
                    for(int j = 0;j < tasks.length();j++) {
                        JSONObject task = tasks.getJSONObject(j);
                        System.out.println("Task Name: " + task.getString("taskName"));
                        System.out.println("Description: " + task.getString("description"));
                        System.out.println("Time Assigned: " + task.getString("timeAssigned"));
                        System.out.println();

                    }
                    break;
                }
                }


        } catch (IOException e) {
        System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void viewEmployeesTasks() {
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\EmployesTasks.json";

        try {
            System.out.println("Your employe's tasks for today are:\n");
            StringBuilder jsonBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                System.out.println("Number of tasks: " + person.getInt("taskCount"));
                System.out.println("Name of employe: " + person.getString("name"));
                JSONArray tasks = person.getJSONArray("tasks");
                    for(int j = 0;j < tasks.length();j++) {
                        JSONObject task = tasks.getJSONObject(j);
                        System.out.println("Task Name: " + task.getString("taskName"));
                        System.out.println("Description: " + task.getString("description"));
                        System.out.println("Time Assigned: " + task.getString("timeAssigned"));
                        System.out.println();

                    }
            }


        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
    public void setEmlpoyeeTaskComplete() {

    }
    @Override
    public void setTaskCompleted() {
        System.out.println("Function called!");
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            boolean found = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                if (person.getString("name").equalsIgnoreCase(this.getName())) {
                    found = true;

                    JSONArray tasks = person.getJSONArray("tasks");

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to delete for " + this.getName());
                        return;
                    }

                    System.out.println("Tasks for " + this.getName() + ":");
                    this.viewTasks();

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the task number you want to delete: ");
                    int taskNumber = scanner.nextInt();

                    if (taskNumber > 0 && taskNumber <= tasks.length()) {
                        tasks.remove(taskNumber - 1);
                        System.out.println("Task " + taskNumber + " has been deleted.");


                        int currentTaskCount = person.getInt("taskCount");
                        person.put("taskCount", currentTaskCount - 1);
                    } else {
                        System.out.println("Invalid task number.");
                        return;
                    }

                    break;
                }
            }

            if (found) {
                // Save the updated JSON back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonArray.toString(4));
                    System.out.println("Changes saved to file.");
                }
            } else {
                System.out.println("User " + this.getName() + " not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing the file: " + e.getMessage());
        }

    }
    public void assignTask() {
        System.out.println("Function called!");
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            boolean found = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                if (person.getString("name").equalsIgnoreCase(this.getName())) {
                    found = true;

                    JSONArray tasks = person.getJSONArray("tasks");

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to delete for " + this.getName());
                        return;
                    }

                    System.out.println("Tasks for " + this.getName() + ":");
                    this.viewTasks();

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the task number you want to delete: ");
                    int taskNumber = scanner.nextInt();

                    if (taskNumber > 0 && taskNumber <= tasks.length()) {
                        tasks.remove(taskNumber - 1);
                        System.out.println("Task " + taskNumber + " has been deleted.");


                        int currentTaskCount = person.getInt("taskCount");
                        person.put("taskCount", currentTaskCount - 1);
                    } else {
                        System.out.println("Invalid task number.");
                        return;
                    }

                    break;
                }
            }

            if (found) {
                // Save the updated JSON back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonArray.toString(4));
                    System.out.println("Changes saved to file.");
                }
            } else {
                System.out.println("User " + this.getName() + " not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing the file: " + e.getMessage());
        }

    }
    public void whileIsLoggedIn() {
        System.out.println("Choose one of the following options:\n");
        while (true) {
            System.out.println("1 - See your tasks.\n");
            System.out.println("2 - See employee's tasks.\n");
            System.out.println("3 - Set one of your tasks as completed.\n");
            System.out.println("4 - Set one of your employee's tasks as completed.\n");
            System.out.println("5 - Assign a task.\n");
            System.out.println("6 - Exit the app.\n");

            InputDevice inputDevice = new InputDevice();
            Integer option = inputDevice.chooseOption();

            if (option == 1) {
                this.viewTasks();
                System.out.println("You can continue by choosing an option again:\n");
            } else if (option == 2) {
                this.viewEmployeesTasks();
                System.out.println("You can continue by choosing an option again:\n");
            } else if (option == 3) {
                this.setTaskCompleted();
                System.out.println("You can continue by choosing an option again:\n");
            } else if(option == 4) {
                this.setEmlpoyeeTaskComplete();
                System.out.println("You can continue by choosing an option again:\n");
            } else if(option == 5) {
                this.assignTask();
                System.out.println("You can continue by choosing an option again:\n");
            } else if(option == 6) {
                System.exit(0);
            } else {
                System.out.println("Enter a valid option!");
            }
        }
    }


}
