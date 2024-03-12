import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

class CardTest {
    @Test
    void testSingleCard() {
        Card card = new Card("spades", 10);
        assertEquals(card.getValue(), 10, "the value is not correct");
        assertEquals(card.getSuit(), "spades", "the suit is not correct");

    }
    @Test
    void testManyCards() {
        ArrayList<Card> deck = new ArrayList<>();
        for(int i = 0; i < 52; i++){
            deck.add(new Card("spades", i));
        }
        int i = 0;
        for(Card card : deck){
            assertEquals(card.getValue(), i, "the value is not correct");
            assertEquals(card.getSuit(), "spades", "the suit is not correct");
            i++;
        }


    }

}
