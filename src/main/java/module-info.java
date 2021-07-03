module project {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.media;

    opens org to javafx.fxml, com.google.gson;
    opens org.model to com.google.gson;
    exports org to javafx.graphics, javafx.media, javafx.base;
    exports org.view to javafx.graphics;
    exports org.model to com.google.gson;

}