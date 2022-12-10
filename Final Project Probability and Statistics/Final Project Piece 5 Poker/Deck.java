import java.util.ArrayList;
import java.util.Random;

public class Deck {
	// ArrayList of cards
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	
	public void fillDeck() {
		// "make" the cards and then add them to the deck
		Card c = null;
		// 4 suits
		for (int i = 1; i <= 4; i++) {
			// 13 ranks
			for (int j = 1; j <= 13; j++) {
				c = new Card(i, j);
				cardDeck.add(c);
			}
		}
	}
	
	// print out the card number, suite, and rank (checker)
	public void printDeck() {
		for(int i = 0; i < cardDeck.size(); i++) {
			System.out.println("Card: " + (i+1) + ", Suite: " + cardDeck.get(i).getSuit() + ", Rank: " + cardDeck.get(i).getRank());
		}
	}
	
	// getter
	public ArrayList<Card> getDeck() {
		return cardDeck;
	}
	
	// card shuffle method
	public void shuffle() {
		Random ran = new Random();
		for(int i = 0; i < cardDeck.size(); i++) {
			// randomize the position to swap the two cards
			int r = ran.nextInt(cardDeck.size() - i) + i;
			
		}
	}
	
	
}
