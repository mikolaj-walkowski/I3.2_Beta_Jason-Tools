module com.example.json_tools_client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.json_tools_client to javafx.fxml;
    exports com.example.json_tools_client;
}