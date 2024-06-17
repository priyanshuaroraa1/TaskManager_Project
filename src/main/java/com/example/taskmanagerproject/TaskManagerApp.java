package com.example.taskmanagerproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class TaskManagerApp extends Application {
    private TaskManager taskManager;
    private FileManager fileManager;
    private ObservableList<Task> taskObservableList;
    private ListView<Task> taskListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        taskManager = new TaskManager();
        fileManager = new FileManager("src/main/resources/com/example/taskmanagerproject/tasks.txt");

        // Load tasks from file
        List<Task> tasks = fileManager.loadTasks();
        taskManager.addAllTasks(tasks);

        BorderPane root = new BorderPane();
        VBox centerBox = new VBox(10);
        centerBox.setPadding(new Insets(10));

        taskObservableList = FXCollections.observableArrayList(taskManager.getAllTasksSorted());
        taskListView = new ListView<>(taskObservableList);

        centerBox.getChildren().addAll(taskListView);

        // Add task form
        TextField descriptionField = new TextField();
        DatePicker datePicker = new DatePicker();
        Spinner<Integer> prioritySpinner = new Spinner<>(1, 10, 1);
        Button addButton = new Button("Add Task");

        addButton.setOnAction(event -> {
            String description = descriptionField.getText();
            LocalDate dueDate = datePicker.getValue();
            int priority = prioritySpinner.getValue();
            Task newTask = new Task(description, dueDate, priority);
            taskManager.addTask(newTask);
            taskObservableList.setAll(taskManager.getAllTasksSorted());
            fileManager.saveTasks(taskManager.getAllTasks());
        });

        VBox addBox = new VBox(10);
        addBox.setPadding(new Insets(10));
        addBox.getChildren().addAll(
                new Label("Add New Task:"),
                new Label("Description:"),
                descriptionField,
                new Label("Due Date:"),
                datePicker,
                new Label("Priority:"),
                prioritySpinner,
                addButton
        );

        root.setCenter(centerBox);
        root.setRight(addBox);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Save tasks to file when application exits
        primaryStage.setOnCloseRequest(event -> {
            fileManager.saveTasks(taskManager.getAllTasks());
        });
    }
}