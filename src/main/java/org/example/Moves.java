package org.example;

import java.util.LinkedList;
import java.util.List;

public class Moves {
    private List<List<Card>> moves;

    public Moves(){
        moves = new LinkedList<>();
    }
    public void addMove(List<Card> cards){
        moves.add(cards);
    }
    public List<Card> getMove(int i){
        return moves.get(i);
    }

    public List<List<Card>> getMoves() {
        return moves;
    }

    public void deleteMoves(){
        moves.removeAll(moves);
    }
    @Override
    public String toString() {
        return "Move{" + "moves=" + moves + '}';
    }
}
