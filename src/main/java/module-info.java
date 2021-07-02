module project {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.media;

    opens org to javafx.fxml, com.google.gson;
    exports org to javafx.graphics, javafx.media, javafx.base;
}