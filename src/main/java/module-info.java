module com.scoutingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.zxing.javase;
    requires java.desktop;
    requires com.google.zxing;

    opens com.scoutingapp to javafx.fxml;
    exports com.scoutingapp;
}
