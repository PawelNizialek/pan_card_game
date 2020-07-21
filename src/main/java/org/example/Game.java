package org.example;

public class Game {
    protected Deck deck = new Deck();
    protected PileOfCards computer = new PileOfCards(deck);
    protected PileOfCards human = new PileOfCards(deck);
    protected PileOfCards pile = new PileOfCards(deck);

    public Game(){

    }
}
