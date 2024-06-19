package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;
import alisher.mc.kz.poker.combinations.Combination;

import java.util.List;


public class Flush extends Combination {
    private Card.Suit suit;
    public Flush(List<Card> checkdeck, Card.Suit suit) {
        super(checkdeck, Game.Combinations.FLUSH);
        this.suit = suit;
    }

    public Card.Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Combination flush) {
        if(this.suit.getScore()>((Flush)flush).getSuit().getScore()){
            return 1;
        }
        else if (this.suit.getScore()==((Flush)flush).getSuit().getScore()){
            for(int i = 4; i >= 0; i --){
                if (checkdeck.get(i).getSuit().getScore() > flush.checkdeck.get(i).getSuit().getScore()){
                    return 1;
                }
                else if(checkdeck.get(i).getSuit().getScore() < flush.checkdeck.get(i).getSuit().getScore()){
                    return 2;
                }
            }
            return 0;
        }
        return 2;
    }


}