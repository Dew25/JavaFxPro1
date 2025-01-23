package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import ee.ivkhkdev.javafxpro1.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class UserController implements Initializable {

    private UserService userService;
    private ObservableList<AppUser> users = FXCollections.observableArrayList();
    @FXML
    private Label lblInfo;
    @FXML
    private TextField tfFirstname;
    @FXML
    private TextField tfLastname;
    @FXML
    private ListView<AppUser> lvListUsers;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void addElement(){
        AppUser savedUser = userService.add(tfFirstname.getText(), tfLastname.getText());
        lblInfo.setText(String.format("Пользователь, %d %s %s, сохранен",
                savedUser.getId(),
                savedUser.getFirstname(),
                savedUser.getLastname()));
        reloadUsers();
    }
    @FXML
    private void deleteElement(){
        lblInfo.setText("");
    }

    private void reloadUsers(){
        List<AppUser> listUsers = userService.getAllUsers();
        users.clear();
        users.addAll(listUsers);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblInfo.setText("Hello World");
        reloadUsers();
        lvListUsers.setItems(users);
        lvListUsers.setCellFactory(lv->new ListCell<AppUser>() {
            @Override
            protected void updateItem(AppUser item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getId() +". "+item.getFirstname() + " " + item.getLastname());
            }
        });
    }
}
