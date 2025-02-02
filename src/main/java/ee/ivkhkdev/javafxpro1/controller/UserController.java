package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.service.UserService;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

    private final SpringFXMLLoader springFXMLLoader;
    private UserService userService;

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

    public UserController(UserService userService, SpringFXMLLoader springFXMLLoader) {
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

    public void loadLoginForm() throws IOException {
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
//        reloadUsers();
//        lvListUsers.setItems(users);
//        lvListUsers.setCellFactory(lv->new ListCell<AppUser>() {
//            @Override
//            protected void updateItem(AppUser item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty ? "" : item.getId() +". "+item.getFirstname() + " " + item.getLastname());
//            }
//        });
    }
}
