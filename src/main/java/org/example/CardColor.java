package org.example;

public enum CardColor {
    HEARTS("HEARTS"),
    CLUBS("CLUBS"),
    DIAMONDS("DIAMONDS"),
    SPADES("SPADES");

    private String cardColor;

    CardColor(String cardColor){
        this.cardColor = cardColor;
    }

    public String getCardColor(){
        return cardColor;
    }
}
