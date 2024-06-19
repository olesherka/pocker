package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.ArrayList;
import java.util.List;

public class PairCard extends Combination {
    private Card.Suit suit;
    public PairCard(List<Card> checkdeck, Card.Suit suit) {
        super(checkdeck, Game.Combinations.PAIR);
        this.suit = suit;
    }

    public Card.Suit getSuit() {
        return suit;
    }

    public List<Card> calculateRestCards(){
        List<Card> restCards = new ArrayList<>();
        for (int i = 0; i < checkdeck.size(); i ++){
            if(checkdeck.get(i).getSuit() != suit){
                restCards.add(checkdeck.get(i));
            }
        }
        return restCards;
    }

    @Override
    public int compareTo(Combination pairCard) {
        if(this.suit.getScore()>((PairCard)pairCard).getSuit().getScore()){
            return 1;
        }
        else if (this.suit.getScore()==((PairCard)pairCard).getSuit().getScore()){
            List<Card> restCard1 = calculateRestCards();
            List<Card> restCard2 = ((PairCard) pairCard).calculateRestCards();
            for(int i = 2; i >= 0; i--){
                if(restCard1.get(i).getSuit().getScore() > restCard2.get(i).getSuit().getScore()){
                    return 1;
                }
                else if(restCard1.get(i).getSuit().getScore() < restCard2.get(i).getSuit().getScore()){
                    return 2;
                }
            }
            return 0;
        }
        return 2;
    }


}
