package ee.ivkhkdev.javafxpro1.controller;

import ee.ivkhkdev.javafxpro1.model.entity.Book;
import ee.ivkhkdev.javafxpro1.service.BookService;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {
    private ObservableList books = FXCollections.observableArrayList();
    private SpringFXMLLoader springFXMLLoader;
    private BookService bookService;
    private MenuController menuController;

    @FXML
    private TableView<Book> tvBooks;
    @FXML private TableColumn<Book, Long> tcId;
    @FXML private TableColumn<Book, String> tcTitle;
    @FXML private TableColumn<Book, Integer> tcPub;
    @FXML private VBox vbMainRoot;


    public MainFormController(BookService bookService, SpringFXMLLoader springFXMLLoader) {
        this.bookService = bookService;
        this.springFXMLLoader = springFXMLLoader;
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
        saveBooks();
        books.addAll(bookService.getBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcPub.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        tvBooks.setItems(books);
        //Назначаем обработчик события выбора записи в таблице
        tvBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                if (newValue != null) {
                    infoMessage("Вы выбрали: " + newValue.getId() + ", Название " + newValue.getTitle());
                }
            }
        });

    }
}
