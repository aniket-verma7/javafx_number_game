module com.numbergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.numbergame to javafx.fxml;
    exports com.numbergame;
    exports com.numbergame.model;
    opens com.numbergame.model to javafx.fxml;
    exports com.numbergame.controller;
    opens com.numbergame.controller to javafx.fxml;
}