package alisher.mc.kz.poker;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final Face face;


    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
    }

    @Override
    public int compareTo(Card card) {
        if (suit.getScore() == card.getSuit().getScore()) {
            return 0;
        } else if (suit.getScore() > card.getSuit().getScore()) {
            return 1;
        } else {
            return -1;
        }
    }

    public enum Suit {
        TWO("2", 2),
        THREE("3",3),
        FOUR("4", 4),
        FIVE("5",5),
        SIX("6",6),
        SEVEN("7",7),
        EIGHT("8",8),
        NINE("9",9),
        TEN("10",10),
        JACK("J",11),
        QUEEN("Q",12),
        KING("K",13),
        ACE("A",14);
        String view;
        int score;
        Suit(String view, int score){
            this.view = view;
            this.score = score;
        }
        String getView(){
            return this.view;
        }
        public int getScore(){
            return this.score;
        }
    }

    public enum Face {
        HEART("♥"),
        PEAK("♠"),
        DIAMOND("♦"),
        CLUB("♣");
        String view;
        Face(String view){
            this.view = view;
        }
        String getView(){
            return view;
        }
    }
    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    @Override
    public String toString(){
        return suit.getView() + face.getView() ;
    }
}