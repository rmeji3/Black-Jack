import java.util.ArrayList;

public class BlackjackGameLogic {
    public String whoWon(ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
        int playerHandTotal = handTotal(playerHand);
        int dealerHandTotal = handTotal(dealerHand);

        boolean playerBust = playerHandTotal > 21;
        boolean dealerBust = dealerHandTotal > 21;
        boolean playerNatural = playerHandTotal == 21 && playerHand.size() == 2;
        boolean dealerNatural = dealerHandTotal == 21 && dealerHand.size() == 2;

        if (playerBust) return "dealer";
        if (dealerBust) return "player";
        if (playerNatural && !dealerNatural)
            return "player";
        if (dealerNatural && !playerNatural)
            return "dealer";
        if (playerNatural && dealerNatural)
            return "push"; // Both have a natural, it's a tie

        // If nobody has a natural and nobody busted, compare the totals directly
        if (playerHandTotal > dealerHandTotal) return "player";
        if (dealerHandTotal > playerHandTotal) return "dealer";

        return "push"; // If none of the above conditions are met, it's a tie
    }

    public int handTotal(ArrayList<Card> hand){
        int handTotal = 0;
        for(Card c : hand){
            handTotal += c.getValue();
        }
        return handTotal;
    }
    public boolean evaluateBankerDraw(ArrayList<Card> hand){
        int bankerTotal = handTotal(hand);
        return bankerTotal <= 16;
    }
}
