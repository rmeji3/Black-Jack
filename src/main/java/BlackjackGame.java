import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;

    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalMoney;
    public BlackjackGame(){
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
        gameLogic = new BlackjackGameLogic();
    }
    public double evaluateWinnings() {
        System.out.println("player: " + playerHand.size());
        System.out.println(gameLogic.handTotal(playerHand));
        System.out.println("banker: " + bankerHand.size());
        if (gameLogic.whoWon(playerHand, bankerHand).compareTo("player")== 0) {
            boolean playerNatural = gameLogic.handTotal(playerHand) == 21 && playerHand.size() == 2;
            System.out.println("you win");

            if (playerNatural) {
                totalMoney += currentBet * 1.5;
                return currentBet * 1.5;
            }

            totalMoney += currentBet;
            return currentBet;
        } else if (gameLogic.whoWon(playerHand, bankerHand).compareTo("dealer") == 0) {
            totalMoney -= currentBet;
            System.out.println("you lose");
            return -currentBet;
        } else {
            System.out.println("draw");
            return 0;
        }
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    public ArrayList<Card> getBankerHand() {
        return bankerHand;
    }
    public void addToPlayerHand(Card card){
        playerHand.add(card);
    }
    public void addToBankerHand(Card card){
         bankerHand.add(card);
    }
}
