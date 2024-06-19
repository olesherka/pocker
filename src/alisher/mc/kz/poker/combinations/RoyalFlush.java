package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;


import java.util.List;


public class RoyalFlush extends Combination {
    private Card.Suit suit;
    public RoyalFlush(List<Card> checkdeck) {
        super(checkdeck, Game.Combinations.ROYAL_FLUSH);
    }

    @Override
    public int compareTo(Combination royalFlush) {
        return 0;
    }
}