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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginFormController implements Initializable {

    private final UserService userService;
    private SpringFXMLLoader springFXMLLoader;
    @FXML private Label lbInfo;
    @FXML private TextField tfLogin;
    @FXML private PasswordField pfPassword;
    @FXML private Button btnEnter;

    public LoginFormController(SpringFXMLLoader springFXMLLoader, UserService userService) {
        this.springFXMLLoader = springFXMLLoader;
        this.userService = userService;
    }
    @FXML private void login() throws IOException {
        if(userService.authenticate(tfLogin.getText(),pfPassword.getText())){
            loadMainForm();
        }else{
            lbInfo.setText("Нет такого пользователя или пароля");
        };
    }

    private void loadMainForm() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/main/mainForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
    }

    @FXML
    private void register() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/javafxpro1.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
    }

    private Stage getPrimaryStage(){
        return JavaFxPro1Application.primaryStage;
    }
    private AppUser getCurrentUser(){ return JavaFxPro1Application.currentUser;}
    public void initSuperUser() {
        userService.setSuperUser();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEnter.setOnAction(event -> {
            try {
                this.login();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


}
