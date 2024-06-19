package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.List;

public class FourOfAKind extends Combination {
    private Card.Suit suit;
    public FourOfAKind(List<Card> checkdeck, Card.Suit suit) {
        super(checkdeck, Game.Combinations.FOUR_OF_A_KIND);
        this.suit = suit;
    }

    public Card.Suit getSuit() {
        return suit;
    }

    public Card calculateRestCards(){
        for (int i = 0; i < checkdeck.size(); i ++){
            if(checkdeck.get(i).getSuit() != suit){
                return checkdeck.get(i);
            }
        }
        return null;
    }

    @Override
    public int compareTo(Combination fourOfAKind) {
        if(this.suit.getScore()>((FourOfAKind)fourOfAKind).getSuit().getScore()){
            return 1;
        }
        else if (this.suit.getScore()==((FourOfAKind)fourOfAKind).getSuit().getScore()){
            Card restCard1 = calculateRestCards();
            Card restCard2 = ((FourOfAKind) fourOfAKind).calculateRestCards();
            if(restCard1.getSuit().getScore() > restCard2.getSuit().getScore()){
                return 1;
            }
            else if(restCard1.getSuit().getScore() < restCard2.getSuit().getScore()){
                return 2;
            }
            return 0;
        }
        return 2;
    }


}