package ee.ivkhkdev.javafxpro1;

import ee.ivkhkdev.javafxpro1.controller.LoginFormController;
import ee.ivkhkdev.javafxpro1.tools.fxmlloader.SpringFXMLLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaFxPro1Application extends Application {
    public static Stage primaryStage;
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(JavaFxPro1Application.class, args);
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFxPro1Application.primaryStage = primaryStage;
        SpringFXMLLoader springFXMLLoader = applicationContext.getBean(SpringFXMLLoader.class);
        FXMLLoader fxmlLoader = springFXMLLoader.load("/ee/ivkhkdev/javafxpro1/login/loginForm.fxml");
        Parent root = fxmlLoader.load();
        LoginFormController controller = fxmlLoader.getController();
        controller.initSuperUser();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
}
