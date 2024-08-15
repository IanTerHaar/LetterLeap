package com.ianterhaar.letterleap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(getClass().getResourceAsStream("/com/ianterhaar/letterleap/images/facIcon.jpg"));
        stage.getIcons().add(icon);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 373, 509);
        stage.setTitle("LetterLeap");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}