import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Hand {
	Deck cDeck; //creates a new deck to work off of(not sure if needed or not but whatevs)
	ArrayList<Card> nHand = new ArrayList<Card>(); //inits a new arraylist to hold the five cards in the hand
	//creation of hand
	public Hand() {
		cDeck = new Deck();
		Random gen = new Random(); //calls in random to pick random cards
		int count = 0; //counter to count how many cards we've picked
		nHand = Deck.getFourOfAKind();
//		while(count < 5) {
//			int r = gen.nextInt(cDeck.getSize()); //generates a random num between 0 and 51
//			Card e = cDeck.getCard(r); //uses method from deck to get card from that location
//			nHand.add(e); //pushes this card onto the new hand
//			count++; //increments count
//		}
	}
	//----End Creation
	//printing of hand
	public void printHand() { //method to print the hand for debugging
		for (int i = 0; i < nHand.size(); i++) {
			System.out.println(nHand.get(i));
		}
		System.out.println(determineValue());
	}
	//----End of Print
	//Grabbing of values of hand
	public String[] getValues() { // grabbing the values of all the cards in the hand in order to check later
		String[] val = new String[5];
		for (int i = 0; i < val.length; i++) {
			val[i] = nHand.get(i).getValue();
			//System.out.println(val[i]);
		}
		return val;
	}
	//Grabbing suits of hand
	public String[] getSuits() { //grabbing suits of the cards in the hand
		String[] suit = new String[5];
		for (int i = 0; i < suit.length; i++) {
			suit[i] = nHand.get(i).getSuit();
			//System.out.println(suit[i]);
		}
		return suit;
	}
	
	//--
	//VALUE CHECKS
	//--
	
	public String determineValue() { //LEAD METHOD
		String[] suits = getSuits(); //grabs suits of hand 
		String[] values = getValues(); //grabs values of hand
		int[] valInt = convertInt(values); //converts this to int array
		valInt = sortByValue(valInt); //sorts array from small to large
		String res = ""; //sets the output string
		
		if (isStFlush(suits, valInt)) {
			res = "Straight Flush";
		}
		if (isFourKind(valInt)) {
			res = "Four of a Kind";
		}
		else {
			res = "junk";
		}
		
		for (int i = 0; i < valInt.length; i++) {
			System.out.println(valInt[i]);
		}
		
		
		
		return res;
	}
	
	public boolean isStFlush(String[] suits, int[] values) {
		boolean flag = true;
		if (sameSuit(suits)) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] == values[0] + i && flag) {
					return true;
				}
				else {
					flag = false;
					break;
				}
			}
		}
		
		return false;
	}
	
	public boolean isFourKind(int[] values) {
		boolean a1, a2;
		
		a1 = values[0] == values[1] &&
			 values[1] == values[2] &&
			 values[2] == values[3];
		
		a2 = values[1] == values[2] &&
			 values[2] == values[3] &&
			 values[3] == values[4];
		
		return (a1 || a2);
	}
	
	
	
	
	
	
	
	//---------
	//HELPING METHODS
	//---------
	public int[] convertInt(String[] a) {
		int[] vals = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (a[i] == "A") {
				a[i] = "1";
			}
			if (a[i] == "J") {
				a[i] = "11";
			}
			if (a[i] == "Q") {
				a[i] = "12";
			}
			if (a[i] == "K") {
				a[i] = "13";
			}
		}
		for (int i = 0; i < a.length; i++) {
			vals[i] = Integer.parseInt(a[i]);
		}
		return vals;
	}
	
	
	public int[] sortByValue(int[] a) {
		int[] sorted = new int[a.length];
		sorted = a;
		Arrays.sort(sorted);
		return sorted;
	}
	
	
	public boolean sameSuit(String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}
		return true;
	}
	
	public int[] convertToInt(String[] a) {
		int[] vals = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (a[i] == "A") {
				a[i] = "1";
			}
			if (a[i] == "J") {
				a[i] = "11";
			}
			if (a[i] == "Q") {
				a[i] = "12";
			}
			if (a[i] == "K") {
				a[i] = "13";
			}
		}
		for (int i = 0; i < vals.length; i++) {
			vals[i] = Integer.parseInt(a[i]);
		}
		return vals;
	}
}
