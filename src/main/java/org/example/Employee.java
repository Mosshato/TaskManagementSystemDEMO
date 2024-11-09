package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class Employee extends User implements Login, Task {
    public Boolean isLoggedIn = false;
    String name;

    Employee(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    @Override
    public void login() {
        System.out.println("You are logged in as an employee");
        this.isLoggedIn = true;
    }

    @Override
    public void logout() {
        System.out.println("You logged out from an Employee user");
    }

    public void setTaskCompleted() {
        StringBuilder jsonBuilder = new StringBuilder();
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\EmployesTasks.json";
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
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonArray.toString(4));
                    System.out.println("Changes saved to file.");
                }
            } else {
                System.out.println("User " + this.getName() + " not found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading or writing the file: " + e.getMessage());
        }
    }

    @Override
    public void viewTasks() {
        System.out.println(this.getName()+"'s tasks for today are:\n");
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\EmployesTasks.json";

        try {
            StringBuilder jsonBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    jsonBuilder.append(line);
                }
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            boolean found = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                if (person.getString("name").equalsIgnoreCase(this.getName())) {
                    found = true;
                    JSONArray tasks = person.getJSONArray("tasks");
                    for (int j = 0; j < tasks.length(); j++) {
                        JSONObject task = tasks.getJSONObject(j);
                        System.out.println("Task Name: " + task.getString("taskName"));
                        System.out.println("Description: " + task.getString("description"));
                        System.out.println("Time Assigned: " + task.getString("timeAssigned"));
                        System.out.println();
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("No tasks found for the name: " + this.getName());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Override
    public void whileIsLoggedIn() {
        System.out.println("Choose one of the following options:\n");
        while (true) {
            System.out.println("1 - See your tasks.\n");
            System.out.println("2 - Set a task as completed.\n");
            System.out.println("3 - Exit the app.\n");

            InputDevice inputDevice = new InputDevice();
            Integer option = inputDevice.chooseOption();

            if (option == 1) {
                this.viewTasks();
                System.out.println("You can continue by choosing an option again:\n");
            } else if (option == 2) {
                this.setTaskCompleted();
                System.out.println("You can continue by choosing an option again:\n");
            } else if (option == 3) {
                System.exit(0);
            } else {
                System.out.println("Enter a valid option.");
            }
        }
    }

    @Override
    public Boolean isloggedin() {
        return this.isLoggedIn;
    }
}
