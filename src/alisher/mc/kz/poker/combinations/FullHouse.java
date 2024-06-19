package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.List;

public class FullHouse extends Combination {
    private Card.Suit pairSuit;
    private Card.Suit threeOfAKindSuit;
    public FullHouse(List<Card> checkdeck, Card.Suit pairSuit, Card.Suit threeOfAKindSuit){
        super(checkdeck, Game.Combinations.FULL_HOUSE);
        this.pairSuit = pairSuit;
        this.threeOfAKindSuit = threeOfAKindSuit;
    }

    @Override
    public int compareTo(Combination fullHouse) {
        if(this.threeOfAKindSuit.getScore()>((FullHouse)fullHouse).threeOfAKindSuit.getScore()){
            return 1;
        }
        else if (this.threeOfAKindSuit.getScore()==((FullHouse)fullHouse).threeOfAKindSuit.getScore()){
            if(this.pairSuit.getScore()>((FullHouse)fullHouse).pairSuit.getScore()){
                return 1;
            }
            else if (this.pairSuit.getScore()==((FullHouse)fullHouse).pairSuit.getScore()) {
                return 0;
            }
        }
        return 2;
    }

}