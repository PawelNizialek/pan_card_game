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
    public List<Card> takeFromPile(){
        List<Card> cardsToTake = new LinkedList<>();
        int MAX_CARD_TO_TAKE = 0;
        for (int i = pileOfCards.size(); i > 1; i--) {
            MAX_CARD_TO_TAKE++;
            cardsToTake.add(pileOfCards.get(pileOfCards.size()-1));
            pileOfCards.remove(pileOfCards.size()-1);
            if(MAX_CARD_TO_TAKE==3){
                break;
            }
        }
        return cardsToTake;
    }
    @Override
    public String toString() {
        System.out.println(pileOfCards);
        return super.toString();
    }
}
