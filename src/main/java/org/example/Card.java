package org.example;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class Card {
    private String value;
    private String color;
    private Image faceCard;
    private Image backCard;
    private int sortHierarchy;
    private int worth;

    public Card(String value, String color, int sortHierarchy, int worth) {
        this.value = value;
        this.color = color;
        try{
            URL patch = this.getClass().getResource("/img/"+value+"_"+color+".png");
            FileInputStream inputStream = new FileInputStream(patch.getFile());
            faceCard = new Image(inputStream);
        }
        catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
        try{
            URL patch = this.getClass().getResource("/img/Card_Back.png");
            FileInputStream inputStream = new FileInputStream(patch.getFile());
            backCard = new Image(inputStream);
        }
        catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
        this.sortHierarchy = sortHierarchy;
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

    public int getSortHierarchy(){
        return sortHierarchy;
    }

    public Image getFaceCard(){
        return faceCard;
    }

    public int getWorth() {
        return worth;
    }

    public Image getBackCard() {
        return backCard;
    }
}
