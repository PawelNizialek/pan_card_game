package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class SampleController {
    public SampleController(){
    }
    @FXML
    private Button button;

    @FXML
    void initialize(){

    }

    @FXML
    public void onActionButton(){
        System.out.println("Hello world");
    }
}
