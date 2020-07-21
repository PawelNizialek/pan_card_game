package org.example;

import java.util.List;

public class Game {
    protected Deck deck = new Deck();
    protected PileOfCards computer = new PileOfCards(deck);
    protected PileOfCards human = new PileOfCards(deck);
    protected PileOfCards pile = new PileOfCards(deck);

    public Game(){

    }
    public boolean ruleGiveChecker(List<Card> cards, List<Card> cardsToThrow){
        //if(pile.getCards().isEmpty() && cardsToThrow.get(0).getHierarchy()!=0) return false;
        if(!pile.getCards().isEmpty() && cardsToThrow.get(0).getWorth()<pile.getCards().get(pile.pileOfCards.size()-1).getWorth()) return false;
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

}
