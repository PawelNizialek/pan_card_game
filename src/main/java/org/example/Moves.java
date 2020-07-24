package org.example;

import java.util.LinkedList;
import java.util.List;

public class Moves {
    private List<List<Card>> moves;
    private static final int NO_CARD_TO_THROW = 25;

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
    private static int firstCardPossibleToThrow(Player player, Player pile){
        for (int i = 0; i < player.getCards().size(); i++) {
            if(pile.getCards().isEmpty()) return 0;
            if(player.getCards().get(i).getWorth()>=pile.getCards().get(pile.getCards().size() - 1).getWorth()){
                return i;
            }
        }
        return NO_CARD_TO_THROW;
    }
    public void possibleMoves(Player player, Player pile){
        List<Card> cardsToMove;
        if(!getMoves().isEmpty()) deleteMoves();
        int worth = -4;
        int repeatedValue = 0;
        for (int j = firstCardPossibleToThrow(player, pile) ; j < player.getCards().size(); j++) {
            if(worth!=player.getCards().get(j).getWorth()){
                repeatedValue = 1;
                cardsToMove = new LinkedList<>();
                cardsToMove.add(player.getCards().get(j));
                worth=player.getCards().get(j).getWorth();
                addMove(cardsToMove);
            }
            else repeatedValue++;
            if(repeatedValue==4){
                cardsToMove = new LinkedList<>();
                cardsToMove.add(player.getCards().get(j));
                cardsToMove.add(player.getCards().get(j-1));
                cardsToMove.add(player.getCards().get(j-2));
                cardsToMove.add(player.getCards().get(j-3));
                addMove(cardsToMove);
            }
        }
    }
    public void deleteMoves(){
        moves.removeAll(moves);
    }
    @Override
    public String toString() {
        return "Move{" + "moves=" + moves + '}';
    }
}
