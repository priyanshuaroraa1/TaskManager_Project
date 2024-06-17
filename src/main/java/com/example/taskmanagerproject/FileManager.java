package com.example.taskmanagerproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(List<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            tasks = (List<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading tasks from file: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found while reading tasks: " + e.getMessage());
            e.printStackTrace();
        }
        return tasks;
    }
}
