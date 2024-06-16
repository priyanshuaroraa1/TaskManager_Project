module com.example.taskmanagerproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.taskmanagerproject to javafx.fxml;
    exports com.example.taskmanagerproject;
}