package alisher.mc.kz.poker;

import alisher.mc.kz.poker.combinations.DeckOfCards;
import alisher.mc.kz.poker.combinations.GameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
    private List<Card>cardsOnTable = new ArrayList<>();

    public Table(DeckOfCards deck) throws GameException {
        this.cardsOnTable = cardsOnTable;
        for (int i = 0; i <=4; i++){
            this.cardsOnTable.add(deck.dealCard());
        }
    }

    public void setCardsOnTable(List<Card>cardsOnTable, int index, Card card){
        this.cardsOnTable.set(index, card);
    }

    public List<Card> getCardsOnTable(){
        Collections.sort(cardsOnTable);
        return cardsOnTable;
    }
    @Override
    public String toString(){
        String s = "Карты на столе : " + '\n';
        s+=getCardsOnTable();
        return s;
    }


}