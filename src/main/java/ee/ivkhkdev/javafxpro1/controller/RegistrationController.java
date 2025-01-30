package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.service.UserService;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RegistrationController implements Initializable {

    private final SpringFXMLLoader springFXMLLoader;
    private final UserService userService;

    @FXML
    private Label lbInfo;
    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField pfPassword;

    public RegistrationController(UserService userService, SpringFXMLLoader springFXMLLoader) {
        this.userService = userService;
        this.springFXMLLoader=springFXMLLoader;
    }

    @FXML
    private void add() throws IOException {
        AppUser savedUser = userService.save(
                tfFirstname.getText(),
                tfLastname.getText(),
                tfLogin.getText(),
                pfPassword.getText()
        );
        lbInfo.setText(String.format("Пользователь, %d %s %s, сохранен",
                savedUser.getId(),
                savedUser.getFirstname(),
                savedUser.getLastname()));
        loadLoginForm();
    }

    private void loadLoginForm() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/login/loginForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
    }

    private static Stage getPrimaryStage() {
        return JavaFxPro1Application.primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
