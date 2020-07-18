package org.example;

public class Game {
    public Game(){
        Deck deck = new Deck();
        Player player = new Player(deck);
        Computer computer = new Computer(deck);
        System.out.println(player);
        System.out.println(computer);
        System.out.println(deck);
    }
}
