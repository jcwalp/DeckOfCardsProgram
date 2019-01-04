import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//Hand Class-----
//Individual Hand Delt
//Methods:
//-printHand() --prints given hand
//-getValues()/getSuit() --returns the suits and values in a given hand inside a string array
//-determineValue() --Determines the cases of the hand using check methods 
//individual value checks-----
//Helpers:
//-convertInt(String[]) --converts the string array values into an int array to make it easier to check cases
//-sortByValue(int[]) --sorts int array from smallest to largest
//-sameSuit(String[]) --returns true if all suits in array are the same


class Hand {
	Deck cDeck; //creates a new deck to work off of(not sure if needed or not but whatevs)
	ArrayList<Card> nHand = new ArrayList<Card>(); //inits a new arraylist to hold the five cards in the hand
	//creation of hand
	public Hand() {
		cDeck = new Deck();
		Random gen = new Random(); //calls in random to pick random cards
		int count = 0; //counter to count how many cards we've picked
		//nHand = Deck.getCertainHand();
		while(count < 5) {
			int r = gen.nextInt(cDeck.getSize()); //generates a random num between 0 and 51
			Card e = cDeck.getCard(r); //uses method from deck to get card from that location
			nHand.add(e); //pushes this card onto the new hand
			count++; //increments count
		}
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
		
		if(isStraight(valInt) && isFlush(suits)) {
			res = "Straight Flush";
		}
		else if (!isStraight(valInt) && isFlush(suits)) {
			res = "Flush";
		}
		else if (isStraight(valInt) && !isFlush(suits)) {
			res = "Straight";
		}
		else if (isFourKind(valInt)) {
			res = "Four of a Kind";
		}
		else if (fullHouse(valInt)) {
			res = "Full House";
		}  
		else if (isThreeKind(valInt)) {
			res = "Three of a Kind";
		}
		else if (twoPair(valInt)) {
			res = "Two Pair";
		}
		else if (onePair(valInt)) {
			res = "One Pair";
		}
		else {
			res = "junk";
		}
		return res;
	}
	/*
	 * Used in finding a flush and a straight flush
	 */
	public boolean isFlush(String[] suits) {
		if (sameSuit(suits)) {
			return true;
		}
		return false;
	}
	/*
	 * Used in finding both a straight and a straight flush
	 */
	public boolean isStraight(int[] values) {
		for (int i = 1; i < values.length; i++) {
			if (values[i] != values[0] + i) {
				return false;
			}
		}
		return true;
	}
	/*
	 * Method that finds a pair of four
	 */
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
	/*
	 * Method that finds full house
	 */
	public boolean fullHouse(int[] values) {
		boolean a1, a2, a3;
		
		a1 = values[0] == values[1] && //checking if the first three cards are the same and the last two are the same
			 values[1] == values[2] &&
			 values[3] == values[4];
		
		a2 = values[1] == values[2] && //checking if the first and last card are a pair and the middle three are the same
			 values[2] == values[3] &&
			 values[0] == values[4];
		
		a3 = values[0] == values[1] && //checks if first two cards are the pair and the other three are the same
			 values[2] == values[3] &&
			 values[3] == values[4];
		
		return (a1 || a2 || a3);
				
	}
	/*
	 * Method that finds three of a kind
	 */
	public boolean isThreeKind(int[] values) {
		boolean a1, a2, a3;
		
		a1 = values[0] == values[1] &&
			 values[1] == values[2] &&
			 values[3] != values[4];
		
		a2 = values[1] == values[2] &&
			 values[2] == values[3] &&
			 values[0] != values[4];
		
		a3 = values[0] != values[1] &&
			 values[2] == values[3] &&
			 values[3] == values[4];
		
		return (a1 || a2 || a3);
	}
	/*
	 * Method that finds two pairs
	 */
	public boolean twoPair(int[] values) {
		boolean a1, a2;
		
		a1 = values[0] == values[1] &&
			 values[2] == values[3];
		
		a2 = values[1] == values[2] &&
			 values[3] == values[4];
		
		return (a1 || a2);
	}
	/*
	 * Method that finds one pair
	 */
	public boolean onePair(int[] values) {
		boolean a1, a2, a3, a4;
		
		a1 = values[0] == values[1];
		
		a2 = values[1] == values[2];
		
		a3 = values[2] == values[3];
		
		a4 = values[3] == values[4];
		
		return(a1 || a2 || a3 || a4);
	}
	
	
	
	
	
	
/*
 * =========HELPER METHODS=========================
 */
	
	/*
	 * Method that converts our original value array of strings
	 * into an int araray  which makes it easier to use
	 * I changed all of the letter values into their numerical constants
	 */
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
	
	/*
	 * Sorts the array sent and then returns it
	 * this makes finding pairs and incrementing values easy
	 */
	public int[] sortByValue(int[] a) {
		int[] sorted = new int[a.length];
		sorted = a;
		Arrays.sort(sorted);
		return sorted;
	}
	
	/*
	 * Method checks to see if all the cards in string array are the same suit
	 */
	public boolean sameSuit(String[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[0] != a[i]) {
				return false;
			}
		}
		return true;
	}
	
}