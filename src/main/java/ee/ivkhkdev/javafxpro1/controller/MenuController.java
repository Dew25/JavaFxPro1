package ee.ivkhkdev.javafxpro1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuController {
    @FXML
    Label lbInfo;
    @FXML
    MenuItem miAdd;

    @FXML private void addBookClick(){
        lbInfo.setText("Hello from miAdd");
    }
    public void setLbInfo(String info){
        lbInfo.setText(info);
    }
}
