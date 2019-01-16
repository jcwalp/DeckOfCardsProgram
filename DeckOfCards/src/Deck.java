import java.util.ArrayList;

class Deck {
	private static final String[] SUITS = {"H", "D", "C", "S"}; //suits
	private static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; //values
	public ArrayList<Card> kCards; //arraylist to hold deck
	
	public Deck() {
		kCards = new ArrayList<Card>(); //init arraylist
		for (String suit : SUITS) { //loop through the two lists and add new cards to the deck
			for (String rank : RANKS) {
				Card nCard = new Card(suit, rank);
				kCards.add(nCard);
			}
		}	
	}
	
	public void printDeck() { //debugging method to print the entire deck
		for (int i = 0; i < kCards.size(); i++) {
			System.out.println(kCards.get(i).toString());
		}
	}
	
	public int getSize() { //method sent to hand class to be able to loop through the deck
		return kCards.size();
	}
	
	public Card getCard(int i) { //method used in hand class to pull a card from deck at a random location
		Card j = kCards.get(i);
		return j;
	}
	
	public static ArrayList<Card> getCertainHand(){
		ArrayList<Card> fourKind = new ArrayList<Card>();
		Card a = new Card("H", "A");
		Card b = new Card("D", "A");
		Card c = new Card("S", "A");
		Card d = new Card("H", "5");
		Card e = new Card("D", "5");
		fourKind.add(a);
		fourKind.add(b);
		fourKind.add(c);
		fourKind.add(d);
		fourKind.add(e);
		return fourKind;
	}

}
