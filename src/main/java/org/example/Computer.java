package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Computer {
    private List<Card> computerCards;
    public Computer(Deck deck){
        computerCards = new LinkedList<>();
        deck.getRandomCards(deck, computerCards);
    }

    @Override
    public String toString() {
        System.out.println(computerCards);
        return super.toString();
    }
}
