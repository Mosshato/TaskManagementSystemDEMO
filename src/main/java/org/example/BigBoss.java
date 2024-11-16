package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BigBoss extends User implements Login, Task{
    Boolean isLoggedin = false;

    BigBoss(String name) {
        super(name);
    }

    @Override
    public void login() {
        System.out.println("You are logged in as a Boss");
        this.isLoggedin = true;
    }

    @Override
    public void logout() {
        System.out.println("You logged out from a Boss user");
    }

    @Override
    public void whileIsLoggedIn() {
        System.out.println("Choose one of the following options:\n");

        Map<Integer, Runnable> menuOptions = new HashMap<>();
        menuOptions.put(1, this::viewEmployesTasks);
        menuOptions.put(2, this::viewManagersTasks);
        menuOptions.put(3, this::setEmpTask);
        menuOptions.put(4, this::setManagerTask);
        menuOptions.put(5, this::assignTaskEmp);
        menuOptions.put(6, this::assignTaskManager);
        menuOptions.put(7, () -> System.exit(0));

        while (true) {
            System.out.println("1 - See employee's tasks.\n");
            System.out.println("2 - See managers's tasks.\n");
            System.out.println("3 - Set one of your employee's tasks as completed.\n");
            System.out.println("4 - Set one of your managers's tasks as completed.\n");
            System.out.println("5 - Assign a task to an employee.\n");
            System.out.println("6 - Assign a task to a manager.\n");
            System.out.println("7 - Exit the app.\n");

            InputDevice inputDevice = new InputDevice();
            Integer option = inputDevice.chooseOption();

            Runnable action = menuOptions.get(option);
            if (action != null) {
                action.run();
                if (option != 7) {
                    System.out.println("You can continue by choosing an option again:\n");
                }
            } else {
                System.out.println("Enter a valid option!");
            }
        }
    }

    private void viewEmployesTasks() {
        Manager manager  = new Manager();
        manager.viewEmployeesTasks();
    }

    private void viewManagersTasks() {
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";

        try {
            System.out.println("Your managers's tasks for today are:\n");
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
                System.out.println("Name of manager: " + person.getString("name"));
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

    private void assignTaskEmp() {
        Manager manager = new Manager();
        manager.assignTask();
    }

    private void assignTaskManager() {
        String filePath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            System.out.println("Your managers' availability:\n");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject manager = jsonArray.getJSONObject(i);
                String name = manager.getString("name");
                int taskCount = manager.getInt("taskCount");

                System.out.println("Manager Name: " + name);
                System.out.println("Number of Tasks: " + taskCount);
                System.out.println();
            }

            System.out.println("Enter the name of the manager you would like to assign a new task:");
            System.out.println("Each manager can have at most 3 tasks simultaneously!");
            Scanner scanner = new Scanner(System.in);
            String managerName = scanner.nextLine();
            boolean canAssign = false;
            boolean found = false;
            int managerIndex = -1;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject manager = jsonArray.getJSONObject(i);
                if (manager.getString("name").equalsIgnoreCase(managerName)) {
                    found = true;
                    if (manager.getInt("taskCount") < 3) {
                        canAssign = true;
                        managerIndex = i;
                        break;
                    } else {
                        throw new TooManyTasksException("Manager " + managerName + " has too many tasks.");
                    }
                }
            }

            if (!found) {
                System.out.println("Manager " + managerName + " not found.");
            } else if (canAssign) {
                try {
                    System.out.println("Enter the new task details for " + managerName + ":");

                    System.out.print("Task Name: ");
                    String taskName = scanner.nextLine();
                    validateStringInput(taskName, "Task Name");

                    System.out.print("Description: ");
                    String description = scanner.nextLine();
                    validateStringInput(description, "Description");

                    System.out.print("Time Assigned <'1 hours'/'2 hours'/'3 hours'/'4 hours'/'5 hours'>: ");
                    String timeAssigned = scanner.nextLine();
                    validateTimeInput(timeAssigned);

                    JSONObject newTask = new JSONObject();
                    newTask.put("taskName", taskName);
                    newTask.put("description", description);
                    newTask.put("timeAssigned", timeAssigned);

                    JSONObject manager = jsonArray.getJSONObject(managerIndex);
                    JSONArray tasks = manager.getJSONArray("tasks");
                    tasks.put(newTask);

                    int currentTaskCount = manager.getInt("taskCount");
                    manager.put("taskCount", currentTaskCount + 1);

                    try (FileWriter fileWriter = new FileWriter(filePath)) {
                        fileWriter.write(jsonArray.toString(4));
                        System.out.println("New task assigned and changes saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error writing to the file: " + e.getMessage());
                    }
                } catch (InvalidTaskInputException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (TooManyTasksException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading or writing the file: " + e.getMessage());
        }
    }

    private void validateTimeInput(String time) throws InvalidTaskInputException {
        if (!time.matches("[1-5] hours")) {
            throw new InvalidTaskInputException("Error: Time must be in the format '1 hours' to '6 hours'.");
        }
    }

    private void validateStringInput(String input, String fieldName) throws InvalidTaskInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidTaskInputException("Error: " + fieldName + " must be a non-empty string.");
        }
    }


    private void setEmpTask() {
        Manager manager = new Manager();
        manager.setEmlpoyeeTaskComplete();
    }

    private void setManagerTask() {
        String ManagersPath = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\ManagersTasks.json";
        this.viewManagersTasks();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the manager's name you would like to complete:");
        String managerName = scanner.nextLine();
        Manager manager = new Manager(managerName);
        StringBuilder jsonBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(ManagersPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonBuilder.toString());
            boolean found = false;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject person = jsonArray.getJSONObject(i);
                if (person.getString("name").equalsIgnoreCase(manager.getName())) {
                    found = true;

                    JSONArray tasks = person.getJSONArray("tasks");

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks to delete for " + manager.getName());
                        return;
                    }

                    manager.viewTasks();

                    System.out.print("Enter the task number you want to delete: ");
                    try {
                        int taskNumber = Integer.parseInt(scanner.nextLine()); // Potential source of NumberFormatException

                        if (taskNumber > 0 && taskNumber <= tasks.length()) {
                            tasks.remove(taskNumber - 1);
                            System.out.println("Task " + taskNumber + " has been deleted.");

                            int currentTaskCount = person.getInt("taskCount");
                            person.put("taskCount", currentTaskCount - 1);
                        } else {
                            System.out.println("Invalid task number.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid numeric task number.");
                        return;
                    }

                    break;
                }
            }

            if (found) {
                try (FileWriter fileWriter = new FileWriter(ManagersPath)) {
                    fileWriter.write(jsonArray.toString(4));
                    System.out.println("Changes saved to file.");
                }
            } else {
                System.out.println("Manager " + managerName + " not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing the file: " + e.getMessage());
        }
    }




    @Override
    public Boolean isloggedin() {
        return this.isLoggedin;
    }

    @Override
    public void viewTasks() {
        System.out.println("Below are the today tasks assigned to the Managers and Employee: \n");
    }

    @Override
    public void setTaskCompleted() {

    }
}
















