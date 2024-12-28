module com.cinnamonshake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens com.cinnamonshake to javafx.fxml;
    exports com.cinnamonshake;
}
