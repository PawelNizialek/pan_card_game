package org.example;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Card> playerCards;
    private String name;
    public Player(Deck deck){
        playerCards = new LinkedList<>();
        deck.getRandomCards(deck, playerCards);
    }
    public List<Card> getCards() {
        return playerCards;
    }

    public void throwCard(List<Card> cardsToThrow){
        playerCards.removeAll(cardsToThrow);
    }
    public void playerPile(AnchorPane anchorPane){
        anchorPane.getChildren().clear();
        List<ImageView> imageViews = new LinkedList<>();
        for (int i = 0; i < playerCards.size(); i++) {
            imageViews.add(new ImageView(playerCards.get(i).getFaceCard()));
            imageViews.get(i).setLayoutX(i*50+300);
            imageViews.get(i).setFitWidth(126.7);
            imageViews.get(i).setFitHeight(175.7);
            int finalI = i;
            imageViews.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println(playerCards.get(finalI));
                }
            });
            anchorPane.getChildren().add(imageViews.get(i));
        }
    }
    @Override
    public String toString() {
        System.out.println(playerCards);
        return super.toString();
    }
}
