package org.example;

import java.util.LinkedList;
import java.util.List;

public class PileOfCards {
    protected List<Card> pileOfCards;
    private String name;
    public PileOfCards(Deck deck){
        pileOfCards = new LinkedList<>();
        deck.getRandomCards(deck, pileOfCards);
    }
    public List<Card> getCards() {
        return pileOfCards;
    }
    public void addCard(List<Card> cards){
        pileOfCards.addAll(cards);
    }

    public void throwCard(List<Card> cardsToThrow){
        pileOfCards.removeAll(cardsToThrow);
    }

    @Override
    public String toString() {
        System.out.println(pileOfCards);
        return super.toString();
    }
}
