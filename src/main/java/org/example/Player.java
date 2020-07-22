package org.example;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Card> pileOfCards;
    public Player(Deck deck){
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
    public void sortCards() {
        for (int i = 0; i < pileOfCards.size(); i++) {
            for (int j = 1; j < pileOfCards.size(); j++) {
                if (pileOfCards.get(j).getSortHierarchy() < pileOfCards.get(j - 1).getSortHierarchy()) {
                    Card temp = pileOfCards.get(j - 1);
                    pileOfCards.set(j - 1, pileOfCards.get(j));
                    pileOfCards.set(j, temp);
                }
            }
        }
    }
    public boolean isWin(){
        if(pileOfCards.isEmpty()) return true;
        else return false;
    }
    @Override
    public String toString() {
        System.out.println(pileOfCards);
        return super.toString();
    }
}
