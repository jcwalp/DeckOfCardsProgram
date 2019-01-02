import java.util.ArrayList;
import java.util.Random;

class Hand {
	Deck cDeck; //creates a new deck to work off of(not sure if needed or not but whatevs)
	ArrayList<Card> nHand = new ArrayList<Card>(); //inits a new arraylist to hold the five cards in the hand
	public Hand() {
		cDeck = new Deck();
		Random gen = new Random(); //calls in random to pick random cards
		int count = 0; //counter to count how many cards we've picked
		
		while(count < 5) {
			int r = gen.nextInt(cDeck.getSize()); //generates a random num between 0 and 51
			Card e = cDeck.getCard(r); //uses method from deck to get card from that location
			nHand.add(e); //pushes this card onto the new hand
			count++; //increments count
		}
	}
	
	public void printHand() { //method to print the hand for debugging
		for (int i = 0; i < nHand.size(); i++) {
			System.out.println(nHand.get(i));
		}
		System.out.println(determineValue());
	}
	 
	public String[] getValues() { // grabbing the values of all the cards in the hand in order to check later
		String[] val = new String[5];
		for (int i = 0; i < val.length; i++) {
			val[i] = nHand.get(i).getValue();
			//System.out.println(val[i]);
		}
		return val;
	}
	
	public String[] getSuits() { //grabbing suits of the cards in the hand
		String[] suit = new String[5];
		for (int i = 0; i < suit.length; i++) {
			suit[i] = nHand.get(i).getSuit();
			//System.out.println(suit[i]);
		}
		return suit;
	}
	
	public String determineValue() {
		String[] suits = getSuits();
		String[] values = getValues();
		String res = "";
		
		//Royal Flush Check
		
		if (royalFlushCheck(suits, values)) {
			res = "Royal Flush";
		}
		if (straightFlushCheck(suits, values)) {
			res = "Straight Flush";
		}
		if (fourKindCheck(values)) {
			res = "Four of a Kind";
		}
		if (fullHouseCheck(values)) {
			res = "Full House";
		}
		if (flushCheck(suits, values)) {
			res = "Flush";
		}
		if (straightCheck(suits, values)) {
			res = "Straight";
		}
		if (threeKindCheck(values)) {
			res = "Three of a Kind";
		}
		if (twoPairCheck(values)) {
			res = "Two Pair";
		}
		if (pairCheck(values)) {
			res = "Pair";
		}
		else {
			res = "junk";
		}
		
		
		return res;
	}
	
	public boolean royalFlushCheck(String[] a, String[] b) {
		String[] rF = {"A", "k", "Q", "J", "10"};
		for (int i = 1; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}
		if (b.equals(rF)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean straightFlushCheck(String[] a, String[] b) {
		if (sameSuit(a)) {
			
		}
		return false;
	}
	
	public boolean fourKindCheck(String[] a) {
		return false;
	}
	
	public boolean fullHouseCheck(String[] a) {
		return false;
	}
	
	public boolean flushCheck(String[] a, String[] b) {
		return false;
	}
	
	public boolean straightCheck(String[] a, String[] b) {
		return false;
	}
	
	public boolean threeKindCheck(String[] a) {
		return false;
	}
	
	public boolean twoPairCheck(String[] a) {
		return false;
	}
	
	public boolean pairCheck(String[] a) {
		return false;
	}
	
	
	public boolean sameSuit(String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}
		return true;
	}
}
