package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JavaFxPro1Controller {
    private final SpringFXMLLoader springFXMLLoader;

    public JavaFxPro1Controller(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    @FXML
    private void menuEnter() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/menu/menuUser/menuEnter/loginForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        JavaFxPro1Application.primaryStage.setScene(scene);
        JavaFxPro1Application.primaryStage.centerOnScreen();
        JavaFxPro1Application.primaryStage.show();

    }
}
