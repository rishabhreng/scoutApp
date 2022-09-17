module com.scoutingapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.scoutingapp to javafx.fxml;
    exports com.scoutingapp;
}
