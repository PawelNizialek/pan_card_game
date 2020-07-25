package org.example;

import java.util.ArrayList;
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
    private Player tempComputer;
    private Player tempHuman;
    private Moves tempComputerMoves;
    private Moves tempHumanMoves;

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
        if(!(cardsToThrow.size()==1||cardsToThrow.size()==4||cardsToThrow.size()==3)) return false;
        for (int i = 0; i < cardsToThrow.size(); i++) {
            int worth = cardsToThrow.get(0).getWorth();
            if(worth != cardsToThrow.get(i).getWorth()){
                return false;
            }
            if((cardsToThrow.size()==3&&(cardsToThrow.get(i).getSortHierarchy()==0||cardsToThrow.get(i).getValue()!="NINE"))){
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
    public void computerTake() {
        List<Card> cardsToTake = new LinkedList<>();
        if (ruleTakeChecker()) {
            cardsToTake = pile.takeFromPile();
            computer.addCard(cardsToTake);
        }
    }
    public void computerMove() {
        computerMoves.possibleMoves(computer, pile);
        int move = bestMoveNumber(computer);
        if(computerMoves.getMoves().isEmpty()||move==TAKE_CARDS){
            computerTake();
            return;
        }
        if(computer.getCards().get(0).getSortHierarchy()==0) move = 0;
        computerMoves.possibleMoves(computer, pile);
        pile.addCard(computerMoves.getMove(move));
        computer.throwCard(computerMoves.getMove(move));
    }


    public void humanTake() {
        List<Card> cardsToTake = new LinkedList<>();
        if (ruleTakeChecker()) {
            cardsToTake = pile.takeFromPile();
            human.addCard(cardsToTake);
        }
    }


    public int bestMoveNumber(Player player) {
        List<Card> cards;
        System.out.println("============");
        int bestEvaluation=-1000;
        int bestMoveNumber=0;
        try {
            tempComputerMoves = (Moves) computerMoves.clone();
            tempHumanMoves = (Moves) humanMoves.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        computerMoves.possibleMoves(computer, pile);

        for (int i = 0; i < computerMoves.getMoves().size(); i++) {
            cards = new LinkedList<>();
            cards = computerMoves.getMove(i);
            System.out.println("computer:");
            System.out.println(cards);
            player.throwCard(cards);
            pile.addCard(cards);
            System.out.println(minimax(0,human,false));
            if(bestEvaluation<minimax(0,human,false)){
                bestEvaluation=minimax(0,human,false);
                bestMoveNumber=i;
            }
//            System.out.println(evaluatePosition(computer));
            pile.throwCard(cards);
            player.addCard(cards);
            computerMoves.possibleMoves(computer, pile);
        }
        if(ruleTakeChecker()){
            System.out.println("computer take:");
            List<Card> cardsToTake = new LinkedList<>();
            cardsToTake = pile.takeFromPile();
            computer.addCard(cardsToTake);
            if(bestEvaluation<minimax(0,human,false)){
                bestEvaluation=minimax(0,human,false);
                bestMoveNumber=TAKE_CARDS;
            }
            System.out.println(minimax(0,human,false));
            computer.throwCard(cardsToTake);
            pile.addCard(cardsToTake);
        }
        return bestMoveNumber;
    }
    public int minimax(int depth, Player player, boolean isMaximising){
        List<Card> cards;
        int evaluation=-100;
        int bestEvaluation=-100;
        if(depth==5){
            return evaluatePosition(player);
        }
        if(isMaximising) {
            bestEvaluation = -100;
            tempComputerMoves.possibleMoves(computer, pile);
            for (int i = 0; i < tempComputerMoves.getMoves().size(); i++) {
                cards = new LinkedList<>();
                cards = tempComputerMoves.getMove(i);
                computer.throwCard(cards);
                pile.addCard(cards);
                evaluation = minimax(depth + 1, human, false);
                pile.throwCard(cards);
                computer.addCard(cards);
                if (bestEvaluation < evaluation) {
                    bestEvaluation = evaluation;
                }
            }
            if (ruleTakeChecker()) {
//                System.out.println("human take:");
                List<Card> cardsToTake = new LinkedList<>();
                cardsToTake = pile.takeFromPile();
                computer.addCard(cardsToTake);
                evaluation = minimax(depth + 1, human, false);
//                System.out.println(evaluation);
                computer.throwCard(cardsToTake);
                pile.addCard(cardsToTake);
                if (bestEvaluation < evaluation) {
                    bestEvaluation = evaluation;
                }
            }
        }
        else {
            bestEvaluation=100;
            tempHumanMoves.possibleMoves(player, pile);
            for (int i = 0; i < tempHumanMoves.getMoves().size(); i++) {
                cards = new LinkedList<>();
                cards = tempHumanMoves.getMove(i);
//                System.out.println("human:");
//                System.out.println(humanMoves.getMove(i));
                player.throwCard(cards);
                pile.addCard(cards);
                evaluation = minimax(depth+1, computer, true);
//                System.out.println(evaluation);
                pile.throwCard(cards);
                player.addCard(cards);
                if(bestEvaluation>evaluation){
                    bestEvaluation=evaluation;
                }
                tempHumanMoves.possibleMoves(player, pile);
            }
            tempHumanMoves.possibleMoves(player, pile);
            if(ruleTakeChecker()){
//                System.out.println("human take:");
                List<Card> cardsToTake = new LinkedList<>();
                cardsToTake = pile.takeFromPile();
                human.addCard(cardsToTake);
                evaluation = minimax(depth+1, computer, true);
//                System.out.println(evaluation);
                human.throwCard(cardsToTake);
                pile.addCard(cardsToTake);
                if(bestEvaluation>evaluation){
                    bestEvaluation=evaluation;
                }
            }
        }
        return bestEvaluation;
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
//        else{
//            if(player.getCards().isEmpty()) return WIN;
//            return mark;
//        }
//        if(player.getCards().isEmpty()) return WIN;
        return mark;
    }
//    public int evaluateTake(Player player){
//        int evaluation = -100000;
//        List<Card> cardsToTake = new LinkedList<>();
//        if(!ruleTakeChecker()) return evaluation;
//        cardsToTake = pile.takeFromPile();
//        player.addCard(cardsToTake);
//        if(evaluation<evaluatePosition(player)){
//            evaluation = evaluatePosition(player);
//        }
//        player.throwCard(cardsToTake);
//        for (int i = cardsToTake.size()-1; i > -1; i--) {
//            pile.addCard(List.of(cardsToTake.get(i)));
//        }
//        return evaluation;
//    }
}
