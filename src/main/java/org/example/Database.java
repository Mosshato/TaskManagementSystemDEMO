package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_PATH = "C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\db"; // Path to the db file

    // Method to initialize the database file
    public static void initialize() {
        File dbFile = new File(DB_PATH);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
                System.out.println("Database file created at " + dbFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database file located at " + dbFile.getAbsolutePath());
        }
    }
    public static boolean taskExists(String taskData) {
        boolean exists = false;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Facultate\\ANUL2_SEM1\\ProgramareIII\\demoTaskApp\\demoTaskApp\\src\\main\\resources\\db"))) {
            String line;
            boolean tasksSection = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("[Tasks]")) {
                    tasksSection = true; // Start reading tasks
                    continue; // Skip the header line
                }
                if (line.startsWith("[") && tasksSection) {
                    break; // Stop reading when reaching another section
                }
                if (tasksSection && !line.startsWith("#") && !line.trim().isEmpty()) {
                    // Compare the task data to see if it already exists
                    if (line.trim().equals(taskData)) {
                        exists = true;
                        break; // Exit the loop if found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists; // Return whether the task exists
    }
    // Method to append data to the db file
    public static void appendData(String data) {
        try (FileWriter writer = new FileWriter(DB_PATH, true)) {
            writer.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read all tasks from the db file
    public static List<String> getTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tasks.add(line); // Add each task entry to the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
