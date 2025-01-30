package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {

    private final SpringFXMLLoader springFXMLLoader;

    @FXML private VBox vbRoot;

    public MainFormController(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/menu/menu.fxml");
        try {
            VBox vb = fxmlLoader.load();
            vbRoot.getChildren().addFirst(vb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
