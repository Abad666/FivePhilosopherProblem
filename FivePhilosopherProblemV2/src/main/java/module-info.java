module com.example.fivephilosopherproblemv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fivephilosopherproblemv2 to javafx.fxml;
    exports com.example.fivephilosopherproblemv2;
}