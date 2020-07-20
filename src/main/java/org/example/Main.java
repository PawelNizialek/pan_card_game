package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
        Game game = new Game();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxml/board.fxml"));
        Pane stackPane = fxmlLoader.load();

        Scene scene = new Scene(stackPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
