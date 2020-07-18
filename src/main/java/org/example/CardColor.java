package org.example;

public enum CardColor {
    SPADES("SPADES"),
    HEARTS("HEARTS"),
    CLUBS("CLUBS"),
    DIAMONDS("DIAMONDS");

    private String cardColor;

    CardColor(String cardColor){
        this.cardColor = cardColor;
    }

    public String getCardColor(){
        return cardColor;
    }
}
