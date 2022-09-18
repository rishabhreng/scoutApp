module com.scoutingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.scoutingapp to javafx.fxml;
    exports com.scoutingapp;
}
