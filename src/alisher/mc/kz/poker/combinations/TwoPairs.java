package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.ArrayList;
import java.util.List;

public class TwoPairs extends Combination {
    private Card.Suit suit;
    private Card.Suit suit2;
    public TwoPairs(List<Card> checkdeck, Card.Suit suit, Card.Suit suit2) {
        super(checkdeck, Game.Combinations.TWO_PAIR);
        this.suit = suit;
        this.suit2 = suit2;
    }

    public Card.Suit getSuit() {
        return suit;
    }
    public Card.Suit getSuit2() {
        return suit2;
    }

    public Card calculateRestCards(){
        for (int i = 0; i < checkdeck.size(); i ++){
            if(checkdeck.get(i).getSuit() != suit && checkdeck.get(i).getSuit() != suit2){
                return checkdeck.get(i);
            }
        }
        return null;
    }

    @Override
    public int compareTo(Combination twoPairsCard) {
        if(this.suit2.getScore()>((TwoPairs)twoPairsCard).getSuit2().getScore()){
            return 1;
        }
        else if (this.suit2.getScore()==((TwoPairs)twoPairsCard).getSuit2().getScore()){
            if(this.suit.getScore()>((TwoPairs)twoPairsCard).getSuit().getScore()){
                return 1;
            }
            else if (this.suit.getScore()==((TwoPairs)twoPairsCard).getSuit().getScore()) {
                Card restCard1 = calculateRestCards();
                Card restCard2 = ((TwoPairs) twoPairsCard).calculateRestCards();
                if(restCard1.getSuit().getScore() > restCard2.getSuit().getScore()){
                    return 1;
                }
                else if(restCard1.getSuit().getScore() < restCard2.getSuit().getScore()){
                    return 2;
                }
                return 0;
            }
        }
        return 2;
    }

}
