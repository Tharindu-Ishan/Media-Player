package lk.ijse.dep11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root= FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Audio Player");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent -> {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to exit?", ButtonType.YES, ButtonType.NO).showAndWait();
            if(buttonType.get()==ButtonType.NO) windowEvent.consume();
        });
}
}
