package org.example;

public class Game {
    protected Deck deck = new Deck();
    protected Computer computer = new Computer(deck);
    protected Human human = new Human(deck);
    protected Pile pile = new Pile();

    public Game(){

    }
}
