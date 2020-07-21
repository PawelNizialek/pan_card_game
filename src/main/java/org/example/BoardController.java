package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.List;

public class BoardController extends Game{
    private List<Card> cardsToThrow = new LinkedList<>();
    private List<Card> cardsToTake = new LinkedList<>();
    private List<ImageView> imageViews;
    public BoardController(){}
    @FXML
    private AnchorPane playerPile;
    @FXML
    private AnchorPane center;
    @FXML
    private AnchorPane computerPile;
    @FXML
    private AnchorPane Ribbon;
    @FXML
    private Button takeButton;
    @FXML
    private Button giveButton;

    @FXML
    void initialize(){

        Background background = new Background(new BackgroundFill(Color.rgb(165,204,238), CornerRadii.EMPTY, Insets.EMPTY));
        Ribbon.setBackground(background);
        new Game();
        constructPile(playerPile, human);
        constructPile(computerPile, computer);
    }
    public void constructPile(AnchorPane placePile, PileOfCards pileOfCards){
        if(!pileOfCards.equals(pile))
        deck.sortCards(pileOfCards.pileOfCards);
        placePile.getChildren().clear();
        imageViews = new LinkedList<>();
        for (int i = 0; i < pileOfCards.pileOfCards.size(); i++) {
            imageViews.add(new ImageView(pileOfCards.pileOfCards.get(i).getFaceCard()));
            imageViews.get(i).setLayoutX(i*40+100);
            imageViews.get(i).setFitWidth(126.7);
            imageViews.get(i).setFitHeight(175.7);
            if(pileOfCards.equals(human)) throwCard(imageViews,i);
            placePile.getChildren().add(imageViews.get(i));
        }
    }
//    public void pile(){
//        center.getChildren().clear();
//        List<ImageView> imageViews = new LinkedList<>();
//        for (int i = 0; i < pile.pileCards.size(); i++) {
//            imageViews.add(new ImageView(pile.pileCards.get(i).getFaceCard()));
//            imageViews.get(i).setLayoutX(i*40+100);
//            imageViews.get(i).setFitWidth(126.7);
//            imageViews.get(i).setFitHeight(175.7);
//            center.getChildren().add(imageViews.get(i));
//        }
//    }
    public void throwCard(List<ImageView> imageViews, int i){
        imageViews.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.4);
                imageViews.get(i).setEffect(colorAdjust);
                cardsToThrow.add(human.pileOfCards.get(i));
                //Card card = human.pileOfCards.get(i);
//                if(!ruleChecker(human.getCards(),cardsToThrow)){
//                    cardsToThrow.removeAll(cardsToThrow);
//                }
            }
        });
    }
    public void send(ActionEvent event){
        if(!ruleGiveChecker(human.getCards(),cardsToThrow)){
            cardsToThrow.removeAll(cardsToThrow);
            constructPile(playerPile,human);
        }
        else{
            pile.addCard(cardsToThrow);
            human.throwCard(cardsToThrow);
            constructPile(center, pile);
            constructPile(playerPile, human);
            cardsToThrow.removeAll(cardsToThrow);
        }

    }
    public void take(ActionEvent event){
        if(ruleTakeChecker()){
            cardsToTake = pile.takeFromPile();
            human.addCard(cardsToTake);
            constructPile(center, pile);
            constructPile(playerPile, human);
        }

    }
}
