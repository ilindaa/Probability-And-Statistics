public class Card {
	private int suit; // 4 suits (clubs, diamonds, hearts, spades)
	private int rank; // 13 ranks (A, 2, ..., 10, J, Q, K)
	
	// constructor
	public Card(int s, int r) {
		suit = s;
		rank = r;
	}
	
	// getters
	public int getSuit() {
		return suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	// setters
	public void setSuit(int s) {
		suit = s;
	}
	
	public void setRank(int r) {
		rank = r;
	}
}
