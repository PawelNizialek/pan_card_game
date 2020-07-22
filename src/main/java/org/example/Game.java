package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {
    protected Deck deck;
    protected Player computer;
    protected Player human;
    protected Player pile;
    private boolean isComputerMove = true;
    private final int NO_CARD_TO_THROW = 25;
    private Moves moves;

    public void setComputerMove(boolean computerMove) {
        isComputerMove = computerMove;
    }

    public boolean isComputerMove() {
        return isComputerMove;
    }
    public Game(){
       deck = new Deck();
       computer = new Player(deck);
       human = new Player(deck);
       pile = new Player(deck);
       moves = new Moves();
    }
    public boolean ruleGiveChecker(List<Card> cards, List<Card> cardsToThrow){
        if(pile.getCards().isEmpty() && cardsToThrow.get(0).getSortHierarchy()!=0) return false;
        if(!pile.getCards().isEmpty() && cardsToThrow.get(0).getWorth()<pile.getCards().get(pile.getCards().size()-1).getWorth()) return false;
        if(!(cardsToThrow.size()==1||cardsToThrow.size()==4)) return false;
        for (int i = 0; i < cardsToThrow.size(); i++) {
            int worth = cardsToThrow.get(0).getWorth();
            if(worth != cardsToThrow.get(i).getWorth()){
                return false;
            }
        }
        return true;
    }
    public boolean ruleTakeChecker(){
        if(pile.getCards().size()<2)
            return false;
        return true;
    }
    public boolean isStartComputer(){
        if(computer.getCards().get(0).getSortHierarchy()==0){
            setComputerMove(true);
            return true;
        }
        setComputerMove(false);
        return false;
    }
    private int firstCardPossibleToThrow(){
        for (int i = 0; i < computer.getCards().size(); i++) {
            if(pile.getCards().isEmpty()) return 0;
            if(computer.getCards().get(i).getWorth()>=pile.getCards().get(pile.getCards().size() - 1).getWorth()){
                return i;
            }
        }
        return NO_CARD_TO_THROW;
    }
    public void computerMove(){
        List<Card> cards;
        int i = cardNumber();
        if(i==NO_CARD_TO_THROW){
            computerTake();
            return;
        }
        possibleMoves(human);
        possibleMoves(computer);
        Random random = new Random();
        int move = random.nextInt(moves.getMoves().size());
        pile.addCard(moves.getMove(move));
        computer.throwCard(moves.getMove(move));
    }
    public int cardNumber(){
        int i = firstCardPossibleToThrow();
        if(computer.getCards().get(0).getSortHierarchy()==0){
            i=0;
        }
        if(i==NO_CARD_TO_THROW){
            i=NO_CARD_TO_THROW;
        }
        return i;
    }
    public void computerTake() {
        List<Card> cardsToTake = new LinkedList<>();
        if (ruleTakeChecker()) {
            cardsToTake = pile.takeFromPile();
            computer.addCard(cardsToTake);
        }
    }
    public void possibleMoves(Player player){
        //int j = firstCardPossibleToThrow();
        List<Card> cardsToMove;
        if(!moves.getMoves().isEmpty()) moves.deleteMoves();
        int worth = -1;
        int repeatedValue = 0;
        for (int j = firstCardPossibleToThrow() ; j < player.getCards().size(); j++) {
            if(worth!=player.getCards().get(j).getWorth()){
                repeatedValue = 1;
                cardsToMove = new LinkedList<>();
                cardsToMove.add(player.getCards().get(j));
                worth=player.getCards().get(j).getWorth();
                moves.addMove(cardsToMove);
            }
            else repeatedValue++;
            if(repeatedValue==4){
                cardsToMove = new LinkedList<>();
                cardsToMove.add(player.getCards().get(j));
                cardsToMove.add(player.getCards().get(j-1));
                cardsToMove.add(player.getCards().get(j-2));
                cardsToMove.add(player.getCards().get(j-3));
                moves.addMove(cardsToMove);
            }
        }
        System.out.println(moves);
//        for (int i = 0; i < ; i++) {
//
//        }
    }
    public void minimax(){

    }
}
