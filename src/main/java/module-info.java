// nothing needs to be exported or opened, as of now
module com.cinnamonshake {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    exports com.cinnamonshake to javafx.graphics;
    opens com.cinnamonshake to javafx.graphics, javafx.fxml, javafx.web;
    exports com.cinnamonshake.models;
    exports com.cinnamonshake.services;
}