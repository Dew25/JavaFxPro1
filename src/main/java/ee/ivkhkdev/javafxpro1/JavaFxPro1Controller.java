package ee.ivkhkdev.javafxpro1;

import ee.ivkhkdev.javafxpro1.entity.AppUser;
import ee.ivkhkdev.javafxpro1.repository.AppUserRepository;
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
public class JavaFxPro1Controller implements Initializable {


    private final UserService userService;
    private final AppUserRepository appUserRepository;
    private ObservableList<AppUser> users = FXCollections.observableArrayList();
    @FXML
    private Label label;
    @FXML
    private ListView lvUserList;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassword;

    public JavaFxPro1Controller(UserService userService, AppUserRepository appUserRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }

    @FXML
    private void buttonClick(){
        label.setText("Hello from JavaFxPro1Controller");
    }
    @FXML
    private void btnServiceClick(){
       if(tfUserName.getText().isEmpty() || tfPassword.getText().isEmpty()) {
           return;
       }
       AppUser appUser = userService.addUser(tfUserName.getText(),tfPassword.getText());
       label.setText(String.format("id:%d username:%s password:%s%n",
               appUser.getId(),
               appUser.getUsername(),
               appUser.getPassword()));
        reloadUsers();
    }
    @FXML
    private void btnDeleteClick(){
        if(lvUserList.getSelectionModel().getSelectedItem() == null){return;}
        AppUser appUser = (AppUser) lvUserList.getSelectionModel().getSelectedItem();
        appUserRepository.delete(appUser);
        reloadUsers();
    }
    private void reloadUsers(){
        List<AppUser> appUsers = userService.getAllUsers();
        users.clear();
        users.addAll(appUsers);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("");
        // Загрузка данных из сервиса или базы данных
        List<AppUser> allUsers = userService.getAllUsers();
        users.clear();
        users.addAll(allUsers);

        // Связывание с TextField
        lvUserList.setItems(users);
        lvUserList.setCellFactory(lv -> new ListCell<AppUser>() {
            @Override
            protected void updateItem(AppUser item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getId() + ". "+item.getUsername()+" "+item.getPassword());
            }
        });
    }
}
