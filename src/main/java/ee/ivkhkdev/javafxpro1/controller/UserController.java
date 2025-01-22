package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {


    private UserService userService;
    @FXML
    private Label label;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void buttonClick(){
        label.setText("Hello from JavaFxPro1Controller");
    }
    @FXML
    private void btmServiceClick(){
        label.setText(userService.sayHello());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Hello World");
    }
}
