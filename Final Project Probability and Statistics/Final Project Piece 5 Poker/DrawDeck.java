/*
 * finish 5 and 6 (stuck) 
 * finish 10
 * make re-shuffle deck method
 * make calculate probabilities method (make success count/trials for each)
 * have those calculations change as T/F - so use global variables
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawDeck {
	private ArrayList<Card> drawnDeck = null;
	Random ran = new Random();
	Deck d = null;
	
	// variable names: (S)C = (same) count
	private int twoSC = 0; // 1
	private int threeSC = 0; // 2
	private int fourSC = 0; // 3
	private int fiveSuiteSC = 0; // 4
	// private int pairThreeC = 0; // 5
	// private int twoPairC = 0; // 6, not of same rank
	private int orderedWSC = 0; // 7
	private int straightSuiteSC = 0; // 8
	private int AJQK10C = 0; // 9
	// private int noneC = 0; // 10
	
	// reshuffle deck and redeal the program
	public void reshuffleRedeal (int n) {
		int count = 1;
		for (int i = 0; i < n; i++) {
			System.out.println("Run " + count);
			// create an instance of the main deck
			d = new Deck();
			
			// need a re-shuffle deck method here
			d.shuffle();
			
			// create an instance of the drawnDeck
			drawnDeck = new ArrayList<Card>();
			
			// deal cards
			draw(5);
			printDrawnDeck();
			// d.printDeck(); // for testing
			
			monteCarloStats(n);
			count++;
		}
	}
	
	// print the Monte Carlo statistics here
	public void monteCarloStats(int n) {
		if(numOfSameCard(2)) {
			twoSC++;
		}
		
		if(numOfSameCard(3)) {
			threeSC++; 
		}
		
		if(numOfSameCard(4)) {
			fourSC++;
		}
		
		if(flush()) {
			fiveSuiteSC++;
		}
		
		// 5
		// 6
		
		if(orderedWS()) {
			orderedWSC++;
		}
		
		if(straightFlush()) {
			straightSuiteSC++;
		}
		
		if(royalFlush()) {
			AJQK10C++;
		}

		// 10
		
		System.out.println("Two Same Card: " + percent(twoSC, n) + "%");
		System.out.println("Three Same Card: " + percent(threeSC, n) + "%");
		System.out.println("Four Same Card: " + percent(fourSC, n) + "%");
		System.out.println("Flush (five suites are in the same suit): " + percent(fiveSuiteSC, n) + "%");
		// 5
		// 6
		System.out.println("Ordered Without Skipping: " + percent(orderedWSC, n) + "%");
		System.out.println("Straight Flush (straight and same suit): " + percent(straightSuiteSC, n) + "%");
		System.out.println("Royal Flush (A,J,Q,K,10, same suit): " + percent(AJQK10C, n) + "%");
		// 10
		
		System.out.println();
	}
	
	public double percent(double count, double n) {
		return ((count/n) * 100);
	}
	
	// calls the deck methods
	public void deck() {
		d.fillDeck();
		// d.printDeck(); // for testing
	}
	
	
	// draw # times from main deck, add that card to the drawn deck and remove it from the main deck
	public void draw(int numOfDraws) {
		deck();
		for (int i = 0; i < numOfDraws; i++) {
			// get the size of the deck - 1 = 0 - max index
			int drawnCard = ran.nextInt(d.getDeck().size()-1);
			// add the drawn card from main deck into the drawn deck
			drawnDeck.add(d.getDeck().get(drawnCard));
			// remove the card from the main deck
			d.getDeck().remove(drawnCard);
		}
	}
	
	// getter
	public ArrayList<Card> getDrawnDeck() {
		return drawnDeck;
	}
	
	// print out the deck that you draw # cards from (checker)
	public void printDrawnDeck() {
		for (int i = 0; i < drawnDeck.size(); i++) {
			System.out.println("Card: " + (i+1) + ", Suite: " + drawnDeck.get(i).getSuit() + ", Rank: " + drawnDeck.get(i).getRank());
		}
	}
	
	// methods to evaluate the 5 drawn cards
	
	// 1-3 uses the same kind of format so I made it into one method; [num] of same card = [num] cards with same rank
	// loop through the drawn deck and compare the cards (no duplicates) for matches and keep track of the count
	// then check if it's searching for 2, 3, or 4 of the same card and return true/false if it meets the required count
	public boolean numOfSameCard(int num) {
		// count is the non-repeated card matches to be considered [num] of the same card
		int count = 0;
		for(int i = 0; i < drawnDeck.size(); i++) {
			for(int j = i+1; j < drawnDeck.size(); j++) {
				if(drawnDeck.get(i).getRank() == drawnDeck.get(j).getRank()) {
					count++;
				}
			}
		}
		
		// easier to visualize the count by drawing it out and finding the unique matches
		if(num == 2) {
			if(count == 1) {
				return true;
			}
		} else if (num == 3) {
			if(count == 3) {
				return true;
			}
		} else if (num == 4) {
			if(count == 6) {
				return true;
			}
		}
		
		return false;
	}
	
	// flush: all 5 suits are of the same suit
	// keep track of the first suit in the deck, loop through the drawn deck
	// if they all have the same suit as the first card then it's a flush return true, else false
	public boolean flush() {
		int suit = drawnDeck.get(0).getSuit();
		int count = 0;
		for(int i = 0; i < drawnDeck.size(); i++) {
			if(drawnDeck.get(i).getSuit() == suit) {
				count++;
			}
		}
		
		if(count == 5) {
			return true;
		} else {
			return false;
		}
	}
	
	// also called boat, full boat, full house: there is a pair and three of a kind (the two are not of same rank)
	/*
	public boolean fullHouse() {
		// check if the cards have the same rank
		// 3 2 3 2 2
		int rank1 = drawnDeck.get(0).getRank();
		int rank2 = -1;
		for(int i = 0; i < drawnDeck.size(); i++) {
			if(drawnDeck.get(i).getRank() != rank1) {
				rank2 = drawnDeck.get(i).getRank();
			} else if(drawnDeck.get(i).getRank() != rank2 && rank2 != -1){
				return false;
			}
		}

	} */
	
	// cards can be ordered without skipping
	public boolean orderedWS() {
		int rank = drawnDeck.get(0).getRank();
		int count = 0;
		for(int i = 0; i < drawnDeck.size(); i++) {
			if(drawnDeck.get(i).getRank() == (rank+i)) {
				count++;
			}
		}
		if(count == drawnDeck.size()) {
			return true;
		} else {
			return false;
		}

	}
	
	// straight flush: cards are straight and suits are the same
	public boolean straightFlush() {
		if(orderedWS() && flush()) {
			return true;
		} else {
			return false;
		}
	}
	
	// royal flush: A, J, Q, K, 10 of same suit
	public boolean royalFlush() {
		// Arrays.asList(...) instead of doing arr.add(#) 5 times
		ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 10, 11, 12, 13));
		
		if(flush()) {
			for(int i = 0; i < drawnDeck.size(); i++) {
				for(int j = 0; i < arr.size(); i++) {
					if(drawnDeck.get(i).getRank() == arr.get(j)) {
						arr.remove(j);
					}
				}
			}
			
			if(arr.isEmpty()) {
				return true;
			}
		}
		// if arr isn't empty or if flush() is false, will return false
		return false;
	}
	
	
}
