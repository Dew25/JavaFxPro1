package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.JavaFxPro1Application;
import ee.ivkhkdev.javafxpro1.model.entity.Book;
import ee.ivkhkdev.javafxpro1.service.BookService;
import ee.ivkhkdev.javafxpro1.service.UserService;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {
    private ObservableList<Book> observableBooks = FXCollections.observableArrayList();
    private final SpringFXMLLoader springFXMLLoader;
    private final BookService bookService;
    private MenuController menuController;

    @FXML private TableView<Book> tvBooks;
    @FXML private TableColumn<Book, Long> tcId;
    @FXML private TableColumn<Book, String> tcTitle;
    @FXML private TableColumn<Book, Integer> tcPub;
    @FXML private VBox vbMainRoot;


public MainFormController(SpringFXMLLoader springFXMLLoader, BookService bookService) {
        this.springFXMLLoader = springFXMLLoader;
        this.bookService = bookService;
}

    private void infoMessage(String message) {
        menuController.setLbInfo(message);
    }

    private void loadMenu() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/menu/menu.fxml");
        try {
            VBox vb = fxmlLoader.load();
            this.menuController = fxmlLoader.getController();
            vbMainRoot.getChildren().addFirst(vb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Этот метод только для тестов и отладки
    private void saveBooks(){
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setPublicationYear(2000);
        bookService.save(book);
        Book book2 = new Book();
        book2.setTitle("Отцы и дети");
        book2.setPublicationYear(2001);
        bookService.save(book2);
        infoMessage("Две книги добавлены в базу");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMenu();//добавляем меню в mainForm
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcPub.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        // Создаем, сохраняем в базу и загружаем книги в tvBooks
        if(bookService.getBooks().isEmpty()){
            saveBooks();
            observableBooks.addAll(bookService.getBooks());
        }
        tvBooks.setItems(observableBooks);
        //Назначаем обработчик события выбора записи в таблице
        tvBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                if (newValue != null) {
                    infoMessage("Вы выбрали: " + newValue.getId() + ", Название " + newValue.getTitle());
                }
            }
        });
        if(JavaFxPro1Application.currentUser != null){
            infoMessage(String.format("Вы вошли как %s с ролями: %s",
                    JavaFxPro1Application.currentUser.getLogin(),
                    Arrays.toString(JavaFxPro1Application.currentUser.getRoles().toArray()))
            );
        }
    }
}
