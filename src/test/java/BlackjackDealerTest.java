import javafx.application.Application;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BlackjackDealerTest {

	@BeforeAll
	public static void setUpClass() throws Exception {
		// Initialize JavaFX runtime
		Platform.startup(() -> {});
	}

	@Test
	void testDeck() {
		BlackjackDealer dealer = new BlackjackDealer();
		assertEquals(52, dealer.deckSize());
	}
	@Test
	void testDeal() {
		BlackjackDealer dealer = new BlackjackDealer();

		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(50, dealer.deckSize());
		assertEquals(2, hand.size());
	}
	@Test
	void testHit() {
		BlackjackDealer dealer = new BlackjackDealer();

		dealer.generateDeck();
		ArrayList<Card> hand = dealer.dealHand();
		hand.add(dealer.drawOne());
		assertEquals(49, dealer.deckSize());
		assertEquals(3, hand.size());
	}
	@Test
	void testShuffle(){
		BlackjackDealer dealer = new BlackjackDealer();

		dealer.generateDeck();
		ArrayList<Card> deck1 = dealer.getDeck();
		dealer.shuffleDeck();
		ArrayList<Card> deck2 = dealer.getDeck();
		assertEquals(deck1.size(), deck2.size());
		boolean same = true;
		for(int i = 0; i < deck1.size(); i++){
			if(deck1.get(i) != deck2.get(i)){
				same = false;
				break;
			}
		}
		assertEquals(true, same);

	}
	@Test
	void testAddCardOne(){
		BlackjackDealer dealer = new BlackjackDealer();

		ArrayList<Card> hand = new ArrayList<Card>();
		dealer.generateDeck();
		for (int i = 0; i < 52; i++){
			dealer.drawOne();
		}
		assertEquals(0, dealer.deckSize());
		dealer.addCard(1, 11, 0);
		hand.add(dealer.drawOne());
		assertEquals(1, hand.size());
		assertEquals(11, hand.get(0).getValue());
		assertEquals("diamonds", hand.get(0).getSuit());
		assertEquals(0, hand.get(0).getRoyalty());
	}
	void testAddCardMany(){
		BlackjackDealer dealer = new BlackjackDealer();

		ArrayList<Card> hand = new ArrayList<Card>();
		dealer.generateDeck();
		for (int i = 0; i < 52; i++){
			dealer.drawOne();
		}
		dealer.addCard(1, 11, 0);
		dealer.addCard(2, 10, 1);
		dealer.addCard(3, 9, 2);

		hand.add(dealer.drawOne());
		hand.add(dealer.drawOne());
		hand.add(dealer.drawOne());
		assertEquals(3, hand.size());
		assertEquals(11, hand.get(0).getValue());
		assertEquals(10, hand.get(1).getValue());
		assertEquals(9, hand.get(2).getValue());
		assertEquals("diamonds", hand.get(0).getSuit());
		assertEquals("clubs", hand.get(1).getSuit());
		assertEquals("spades", hand.get(2).getSuit());
		assertEquals(0, hand.get(0).getRoyalty());
		assertEquals(1, hand.get(1).getRoyalty());
		assertEquals(2, hand.get(2).getRoyalty());

	}
}
