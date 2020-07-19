package org.example;

import java.util.List;

public class Pile {
    private List<Card> pileCards;
    public Pile(){

    }
    public List<Card> getPileCards() {
        return pileCards;
    }
    @Override
    public String toString() {
        System.out.println(pileCards);
        return super.toString();
    }
}
