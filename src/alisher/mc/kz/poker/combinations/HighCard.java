package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;
import alisher.mc.kz.poker.combinations.Combination;

import java.util.List;

public class HighCard extends Combination {
    public HighCard(List<Card> checkdeck) {
        super(checkdeck, Game.Combinations.HIGH_CARD);
    }

    @Override
    public int compareTo(Combination highCard) {
        for(int i = 4; i >= 0; i--){
            if(checkdeck.get(i).getSuit().getScore() > highCard.checkdeck.get(i).getSuit().getScore()){
                return 1;
            }
            else if(checkdeck.get(i).getSuit().getScore() < highCard.checkdeck.get(i).getSuit().getScore()){
                return 2;
            }
        }
        return 0;
    }

}