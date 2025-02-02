package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuController implements Initializable {
    private UserController userController;
    @FXML private MenuBar menuBar;
    @FXML private Menu mBooks;
    @FXML private Menu mUsers;
    @FXML private Menu mManagers;
    @FXML private Menu mAdministrators;
    @FXML
    Label lbInfo;
    @FXML
    MenuItem miAdd;

    public MenuController(UserController userController) {
        this.userController = userController;
    }

    @FXML private void addBookClick(){
        lbInfo.setText("Hello from miAdd");
    }
    public void setLbInfo(String info){
        lbInfo.setText(info);
    }

    @FXML private void logout() throws IOException {
        lbInfo.setText("Вы вышли");
        JavaFxPro1Application.currentUser = null;
        userController.loadLoginForm();
    }

    private void showMenuForRole(){
        if(JavaFxPro1Application.currentUser.getRoles().contains(JavaFxPro1Application.ROLE.ADMINISTRATOR.toString())){
            mBooks.setVisible(true);
            mUsers.setVisible(true);
            mManagers.setVisible(true);
            mAdministrators.setVisible(true);
            return;
        }
        if(JavaFxPro1Application.currentUser.getRoles().contains(JavaFxPro1Application.ROLE.MANAGER.toString())){
            mBooks.setVisible(true);
            mUsers.setVisible(true);
            mManagers.setVisible(true);
            return;
        }
        if(JavaFxPro1Application.currentUser.getRoles().contains(JavaFxPro1Application.ROLE.USER.toString())){
            mUsers.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showMenuForRole();
    }
}
