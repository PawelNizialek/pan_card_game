package org.example;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.List;

public class BoardController extends Game{
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
    void initialize(){

        Background background = new Background(new BackgroundFill(Color.rgb(165,204,238), CornerRadii.EMPTY, Insets.EMPTY));
        Ribbon.setBackground(background);
        new Game();
        constructPile(playerPile, human);
        constructPile(computerPile, computer);
    }
    public void constructPile(AnchorPane placePile, PileOfCards pileOfCards){
        placePile.getChildren().clear();
        List<ImageView> imageViews = new LinkedList<>();
        for (int i = 0; i < pileOfCards.pileOfCards.size(); i++) {
            if(pileOfCards.equals(pile)) imageViews.add(new ImageView(pileOfCards.pileOfCards.get(i).getFaceCard()));
                else imageViews.add(new ImageView(pileOfCards.pileOfCards.get(i).getFaceCard()));
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
                System.out.println(human.pileOfCards.get(i));
                Card card = human.pileOfCards.get(i);
                pile.addCard(List.of(card));
                human.throwCard(List.of(card));
                constructPile(center, pile);
                constructPile(playerPile, human);
            }
        });
    }
}
