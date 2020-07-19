package org.example;

public class Game {
    public Game(){
        Deck deck = new Deck();
        Human player = new Human(deck);
        Computer computer = new Computer(deck);
        System.out.println(player);
        System.out.println(computer);
        System.out.println(deck);
    }
}
