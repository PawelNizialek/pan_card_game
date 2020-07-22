package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application{
    public static Scene scene;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxml/board.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        scene = new Scene(anchorPane);

        primaryStage.setScene(scene);
        primaryStage.show();
        Background background = new Background(new BackgroundFill(Color.rgb(62,110,64), CornerRadii.EMPTY, Insets.EMPTY));
        anchorPane.setBackground(background);
    }
}
