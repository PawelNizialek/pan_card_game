package org.example;

import java.util.List;

public class Game {
    protected Deck deck;
    protected Player computer;
    protected Player human;
    protected Player pile;
    private boolean isComputerMove = true;

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

    }
    public boolean ruleGiveChecker(List<Card> cards, List<Card> cardsToThrow){
        //if(pile.getCards().isEmpty() && cardsToThrow.get(0).getHierarchy()!=0) return false;
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
    public void isStartComputer(){
        if(computer.getCards().get(0).getSortHierarchy()==0){
            setComputerMove(true);
        }
        else setComputerMove(false);
    }

}
