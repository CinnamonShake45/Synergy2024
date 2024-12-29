module com.cinnamonshake {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens com.cinnamonshake to javafx.fxml;
    exports com.cinnamonshake;
    exports com.cinnamonshake.services;
    exports com.cinnamonshake.models;
}
