package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Player{
    private List<Card> playerCards;
    public Player(Deck deck){
        playerCards = new LinkedList<>();
        deck.getRandomCards(deck, playerCards);
    }

    @Override
    public String toString() {
        System.out.println(playerCards);
        return super.toString();
    }
}
