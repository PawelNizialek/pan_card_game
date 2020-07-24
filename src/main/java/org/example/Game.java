package org.example;

import java.util.LinkedList;
import java.util.List;

public class Game {
    protected Deck deck;
    protected Player computer;
    protected Player human;
    protected Player pile;
    private Moves computerMoves;
    private Moves humanMoves;
    private boolean isComputerMove = true;
    private final int TAKE_CARDS = 25;
    private final int WIN = 10000;

    public Game(){
        deck = new Deck();
        computer = new Player(deck);
        human = new Player(deck);
        pile = new Player(deck);
        computerMoves = new Moves();
        humanMoves = new Moves();
    }
    public void setComputerMove(boolean computerMove) {
        isComputerMove = computerMove;
    }

    public boolean isComputerMove() {
        return isComputerMove;
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

    public void computerMove() {
        computerMoves.possibleMoves(computer, pile);
        int move = bestMoveNumber(computer);
        if(computerMoves.getMoves().isEmpty()||move==TAKE_CARDS){
            computerTake();
            return;
        }
        if(computer.getCards().get(0).getSortHierarchy()==0) move = 0;
        pile.addCard(computerMoves.getMove(move));
        computer.throwCard(computerMoves.getMove(move));
    }

    public void computerTake() {
        List<Card> cardsToTake = new LinkedList<>();
        if (ruleTakeChecker()) {
            cardsToTake = pile.takeFromPile();
            computer.addCard(cardsToTake);
        }
    }

    public int bestMoveNumber(Player player) {
        //Player tempHuman = clone();
        List<Card> temporaryListCard;
        computerMoves.possibleMoves(player, pile);
        int bestMoveNumber = 0;
        int bestEvaluation = -100;
        for (int i = 0; i < computerMoves.getMoves().size(); i++) {
            computerMoves.possibleMoves(player, pile);
            temporaryListCard = computerMoves.getMove(i);
            player.throwCard(temporaryListCard);
            int minimax= minimax(0, human,false);
            System.out.println(minimax);
            int evaluation = evaluatePosition(computer);
            if(bestEvaluation<evaluation){
                bestEvaluation = evaluatePosition(player);
                bestMoveNumber=i;
            }
            player.addCard(temporaryListCard);
            player.sortCards();
        }
        if(bestEvaluation<evaluateTake(player)){
            bestMoveNumber= TAKE_CARDS;
        }
        System.out.println("==========");
        return bestMoveNumber;
    }
    public int minimax(int depth, Player player, boolean isMaximising){
        List<Card> temporaryListCard;
        int bestScore;
        if(evaluatePosition(player)==WIN) return WIN;
        if(evaluatePosition(player)==-WIN) return -WIN;
        if(depth==1) return evaluatePosition(player);
        if(isMaximising){
            bestScore = -WIN;
            for (int i = 0; i < computerMoves.getMoves().size(); i++) {
                temporaryListCard = computerMoves.getMove(i);
                computer.throwCard(temporaryListCard);
                int evaluation = minimax(depth+1,computer,false);
                computer.addCard(temporaryListCard);
                if(evaluation>bestScore){
                    bestScore = evaluation;
                }
            }
        }
        else{
            bestScore = WIN;
            humanMoves.possibleMoves(human, pile);
            for (int i = 0; i < humanMoves.getMoves().size(); i++) {
                temporaryListCard = humanMoves.getMove(i);
//                player.throwCard(temporaryListCard);
//                int evaluation = minimax(depth+1,human,true);
//                player.addCard(temporaryListCard);
//                if(evaluation<bestScore){
//                    bestScore = evaluation;
//                }
            }
        }
        return bestScore;
    }
    public int evaluatePosition(Player player){
        int mark = 0;
        for (int i = 0; i < player.getCards().size(); i++) {
            mark+=player.getCards().get(i).getWorth();
        }
        if(player.equals(human)){
            if(player.getCards().isEmpty()) return -WIN;
            return -mark;
        }
        else{
            if(player.getCards().isEmpty()) return WIN;
            return mark;
        }
    }
    public int evaluateTake(Player player){
        int evaluation = -100000;
        List<Card> cardsToTake = new LinkedList<>();
        if(!ruleTakeChecker()) return evaluation;
        cardsToTake = pile.takeFromPile();
        player.addCard(cardsToTake);
        if(evaluation<evaluatePosition(player)){
            evaluation = evaluatePosition(player);
        }
        player.throwCard(cardsToTake);
        for (int i = cardsToTake.size()-1; i > -1; i--) {
            pile.addCard(List.of(cardsToTake.get(i)));
        }
        return evaluation;
    }
}
