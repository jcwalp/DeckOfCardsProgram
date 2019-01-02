
class Card {
	
	String kValue; //card has a value
	String kSuit; //card has a suit
	
	public Card(String suit, String value) { //constructor for the card
		kSuit = suit;
		kValue = value;
	}
	
	public String getValue() { //getter and setter
		return kValue;
	}
	
	public String getSuit() {
		return kSuit;
	}
	
	public String toString() { //to string method for card
		return String.format("%s of %s", kValue, kSuit);
	}
}
