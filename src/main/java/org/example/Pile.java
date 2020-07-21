package org.example;

import java.util.LinkedList;
import java.util.List;

public class Pile {
    protected List<Card> pileCards;
    public Pile(){
        pileCards = new LinkedList<>();
    }
    public List<Card> getPileCards() {
        return pileCards;
    }
    public void addCard(List<Card> cards){
        pileCards.addAll(cards);
    }
    @Override
    public String toString() {
        System.out.println(pileCards);
        return super.toString();
    }
}
