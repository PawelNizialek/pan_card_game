package org.example;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import java.util.List;

public class BoardController {
    private List<Card> cardsToThrow = new LinkedList<>();
    private List<Card> cardsToTake = new LinkedList<>();
    private List<ImageView> imageViews;
    private Game game;

    public BoardController(){}
    @FXML
    private AnchorPane personPlace;
    @FXML
    private AnchorPane center;
    @FXML
    private AnchorPane computerPlace;
    @FXML
    private AnchorPane ribbon;
    @FXML
    private Button takeButton;
    @FXML
    private Button giveButton;

    @FXML
    void initialize(){

        Background background = new Background(new BackgroundFill(Color.rgb(165,204,238), CornerRadii.EMPTY, Insets.EMPTY));
        ribbon.setBackground(background);
        game = new Game();
        constructPile(personPlace, game.human);
        constructPile(computerPlace, game.computer);
        if(game.isStartComputer()){
            computerMove();
        }
        game.setComputerMove(false);
    }
    public void constructPile(AnchorPane placePile, Player player){
        if(!player.equals(game.pile))
        player.sortCards();
        placePile.getChildren().clear();
        imageViews = new LinkedList<>();
        for (int i = 0; i < player.getCards().size(); i++) {
            if(player.equals(game.computer)){
                imageViews.add(new ImageView(player.getCards().get(i).getBackCard()));
            }
            else imageViews.add(new ImageView(player.getCards().get(i).getFaceCard()));
            imageViews.get(i).setLayoutX(i*40);
            //imageViews.get(i).setFitWidth(126.7);
            //imageViews.get(i).setFitHeight(175.7);
            if(player.equals(game.human)) selectCardsToThrow(imageViews,i);
            placePile.getChildren().add(imageViews.get(i));
        }
    }
    public void selectCardsToThrow(List<ImageView> imageViews, int i){
        imageViews.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(game.isComputerMove()) return;
                ColorAdjust colorAdjust = new ColorAdjust();
                colorAdjust.setBrightness(-0.4);
                imageViews.get(i).setEffect(colorAdjust);
                if(cardsToThrow.contains(game.human.getCards().get(i))){
                    cardsToThrow.remove(game.human.getCards().get(i));
                    colorAdjust.setBrightness(0);
                    imageViews.get(i).setEffect(colorAdjust);
                }
                else cardsToThrow.add(game.human.getCards().get(i));
            }
        });
    }
    public void okButton(){
        if(game.isComputerMove()) return;
        if(!game.ruleGiveChecker(game.human.getCards(),cardsToThrow)){
            cardsToThrow.removeAll(cardsToThrow);
            constructPile(personPlace,game.human);
        }
        else{
            game.pile.addCard(cardsToThrow);
            game.human.throwCard(cardsToThrow);
            constructPile(center, game.pile);
            constructPile(personPlace, game.human);
            cardsToThrow.removeAll(cardsToThrow);
            computerMove();
        }
    }
    public void takeButton(){
        if(game.isComputerMove()) return;
        if(game.ruleTakeChecker()){
            cardsToTake = game.pile.takeFromPile();
            game.human.addCard(cardsToTake);
            constructPile(center, game.pile);
            constructPile(personPlace, game.human);
            computerMove();
        }
    }
    public void reset(){
        game.pile.throwCard(game.pile.getCards());
        constructPile(center,game.pile);
        initialize();
    }
    public void computerMove(){
        game.computerMove();
        constructPile(center,game.pile);
        constructPile(computerPlace,game.computer);
    }
}
