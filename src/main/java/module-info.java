module com.ianterhaar.letterleap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.ianterhaar.letterleap to javafx.fxml;
    exports com.ianterhaar.letterleap;
}