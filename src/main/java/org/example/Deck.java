package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck{
    private List<Card> cards;
    private final Integer NUMBER_OF_CARDS_IN_DECK = 24;

    public Deck(){
        cards = new LinkedList<>();
        int i = 0;
        int j = 0;
        int[] worth = new int[] {-8,-3,-1,1,3,8};
        for(CardValue cardValue : CardValue.values()){
            for(CardColor cardColor : CardColor.values()){
                cards.add(new Card(cardValue.getCardValue(), cardColor.getCardColor(), i++, worth[j]));
            }
            j++;
        }
    }


    public void getRandomCards(Deck deck, List<Card> cardsList){
        Random rand = new Random();
        if(!(deck.getCards().isEmpty()))
        for(int i=0; i<NUMBER_OF_CARDS_IN_DECK/2; i++){
            int randomIndex = rand.nextInt(deck.getCards().size());
            Card randomElement = deck.getCards().get(randomIndex);
            cardsList.add(randomElement);
            deck.getCards().remove(randomIndex);
        }
    }

    @Override
    public String toString() {
        System.out.println(cards);
        return super.toString();
    }

    public List<Card> getCards() {
        return cards;
    }
}
