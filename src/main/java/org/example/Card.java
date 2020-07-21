package org.example;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Card {
    private String value;
    private String color;
    private Image faceCard;
    private int hierarchy;
    private int worth;

    public Card(String value, String color, int hierarchy, int worth) {
        this.value = value;
        this.color = color;
        try{
            FileInputStream inputStream = new FileInputStream("C:\\Users\\Pawel\\Documents\\pan_card_game\\src\\main\\java\\org\\example\\img\\"+value+"_"+color+".png");
            faceCard = new Image(inputStream);
        }
        catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
        this.hierarchy = hierarchy;
        this.worth = worth;
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

    public int getHierarchy(){
        return hierarchy;
    }

    public Image getFaceCard(){
        return faceCard;
    }

    public int getWorth() {
        return worth;
    }

}
