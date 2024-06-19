package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.List;

public class StraightFlush extends Combination {
    private Card.Suit suit;
    public StraightFlush(List<Card> checkdeck, Card.Suit suit) {
        super(checkdeck, Game.Combinations.STRAIGHT_FLUSH);
        this.suit = suit;
    }

    public Card.Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Combination straightFlush) {
        if(this.suit.getScore()>((StraightFlush)straightFlush).getSuit().getScore()){
            return 1;
        }
        else if (this.suit.getScore()==((StraightFlush)straightFlush).getSuit().getScore()){
            return 0;
        }
        return 2;
    }


}
