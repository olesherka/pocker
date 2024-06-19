package alisher.mc.kz.poker.combinations;


import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.List;

public class Straight extends Combination {
    private Card.Suit suit;
    public Straight(List<Card> checkdeck, Card.Suit suit) {
        super(checkdeck, Game.Combinations.STRAIGHT);
        this.suit = suit;
    }

    public Card.Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Combination straight) {
        if(this.suit.getScore()>((Straight)straight).getSuit().getScore()){
            return 1;
        }
        else if (this.suit.getScore()==((Straight)straight).getSuit().getScore()){
            return 0;
        }
        return 2;
    }


}
