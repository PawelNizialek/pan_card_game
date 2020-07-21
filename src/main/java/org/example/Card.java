package org.example;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Card {
    private String value;
    private String color;
    private Image faceCard;

    public Card(String value, String color) {
        this.value = value;
        this.color = color;
        try{
            FileInputStream inputStream = new FileInputStream("C:\\Users\\Pawel\\Documents\\pan_card_game\\src\\main\\java\\org\\example\\img\\"+value+"_"+color+".png");
            faceCard = new Image(inputStream);
        }
        catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return value + " " + color;
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public Image getFaceCard(){
        return faceCard;
    }
}
