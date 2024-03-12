import java.util.ArrayList;
import java.util.Collections;

public class BlackjackDealer {
    private ArrayList<Card> deck;
    public BlackjackDealer() {
        deck = new ArrayList<>();
        generateDeck();
    }



    public void addCard(int suit, int value, int royalty) {
        String suitName = "";
        if (suit == 0){
            suitName = "hearts";
        } else if (suit == 1){
            suitName = "diamonds";
        } else if (suit == 2){
            suitName = "clubs";
        } else if (suit == 3){
            suitName = "spades";
        }
        Card card = new Card(suitName, value);
        card.setRoyalty(royalty);
        deck.add(card);
    }
    public void generateDeck() {
        // Generate a deck of cards
        //royalty numbers
        // 1 = king
        // 2 = queen
        // 3 = jack
        // 0 = no royalty
        deck.clear();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                if (j == 1){
                    addCard(i, 11, 0);
                }
                else if(j <= 10){
                    addCard(i, j, 0);
                }
                else
                    addCard(i, 10, j - 10);

            }
        }
    }
    // king = 11
    // queen = 12
    // jack = 13
    // ace = 1
    // if val >= 10 < 13, val = 10
    public ArrayList<Card> dealHand() {
        // Deal a hand of cards
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(drawOne());
        hand.add(drawOne());
        return hand;
    }
    public void shuffleDeck() {
        // Shuffle the deck
        Collections.shuffle(deck);
    }
    public Card drawOne() {
        // Draw a card from the deck
        return deck.remove(0);
    }
    public int deckSize() {
        // Return the number of cards left in the deck
        return deck.size();
    }
    public ArrayList<Card> getDeck(){
        return deck;
    }


}
