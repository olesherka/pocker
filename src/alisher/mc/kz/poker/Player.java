package alisher.mc.kz.poker;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.combinations.Combination;
import alisher.mc.kz.poker.combinations.DeckOfCards;
import alisher.mc.kz.poker.combinations.GameException;
import alisher.mc.kz.poker.combinations.NullCombination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String playerName;

    private List<Card> cardsOnHands = new ArrayList<>();
    Combination combination = new NullCombination();

    public void setCardsOnHands(List<Card> cardsOnHands) {
        this.cardsOnHands = cardsOnHands;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    public Player(String playerName, DeckOfCards deck) throws GameException {
        this.playerName = playerName;
        for (int i = 0; i <=1; i++){
            this.cardsOnHands.add(deck.dealCard());
        }
        Collections.sort(cardsOnHands);
    }

    public void setCardsOnHands(int index, Card card){
        this.cardsOnHands.set(index, card);
    }

    public String getPlayerName() {
        return playerName;

    }


    public List<Card> getCardsOnHands(){
        return cardsOnHands;
    }
    @Override
    public String toString(){
        String s = "Карты на руках у " + getPlayerName() + ':' + '\n';
        s+=cardsOnHands;
        return s;
    }

}
