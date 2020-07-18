package org.example;

public enum CardValue{
    NINE("NINE"),
    TEN("TEN"),
    JACK("JACK"),
    QUEEN("QUEEN"),
    KING("KING"),
    ACE("ACE");

    private String cardValue;

    CardValue(String cardValue){
        this.cardValue = cardValue;
    }

    public String getCardValue(){
        return cardValue;
    }
}
