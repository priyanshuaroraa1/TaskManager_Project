package com.example.taskmanagerproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getAllTasksSorted() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks);
        return sortedTasks;
    }

    public void addAllTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}
