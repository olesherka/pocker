package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeckOfCards {
    private List<Card> deck = new ArrayList<>();
    static final int NUMBERS_OF_CARDS = 52;
    public DeckOfCards(){
        Card.Suit[] massSuit = Card.Suit.values();
        Card.Face[] massFace = Card.Face.values();
        for(int count = 0; count < NUMBERS_OF_CARDS; count++){
            this.deck.add(new Card(massSuit[count % 13], massFace[count / 13]));
        }
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public void shuffle() {
        for (int first = 0; first < deck.size(); first++) {
            int second = ThreadLocalRandom.current().nextInt(NUMBERS_OF_CARDS);
            Card timecard = deck.get(first);
            deck.set(first, deck.get(second));
            deck.set(second, timecard);
        }
    }
    public Card dealCard() throws GameException {
        if (deck.size() != 0) {
            return deck.remove(0);
        } else {
            throw new GameException("Карт не осталось");
        }
    }
    @Override
    public String toString(){
        String s = " Колода  : \n ";
        s+= deck;
        return s;
    }

}