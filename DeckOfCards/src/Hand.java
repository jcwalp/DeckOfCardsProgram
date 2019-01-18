import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
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
	Scanner kb = new Scanner(System.in);
	Deck cDeck; //creates a new deck to work off of(not sure if needed or not but whatevs)
	ArrayList<Card> nHand = new ArrayList<>(); //inits a new arraylist to hold the five cards in the hand
	Random r = new Random();
	//creation of hand
	public Hand() {
		cDeck = new Deck();
		nHand = Deck.getCertainCard();
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
//			System.out.println(val[i]);
		}
		return val;
	}
	//Grabbing suits of hand

	public String[] getSuits() { //grabbing suits of the cards in the hand
		String[] suit = new String[5];
		for (int i = 0; i < suit.length; i++) {
			suit[i] = nHand.get(i).getSuit();
//			System.out.println(suit[i]);
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
		boolean caseFound = false;
		while(!caseFound) {
			if (isStraight(valInt) && isFlush(suits)) {
				res = "Straight Flush";
				break;
			} else if (!isStraight(valInt) && isFlush(suits)) {
				res = "Flush";
				break;
			} else if (isStraight(valInt) && !isFlush(suits)) {
				res = "Straight";
				break;
			} else if (findPairs(valInt) == 5) {
				res = "Full House";
				break;
			} else if (findPairs(valInt) == 4) {
				res = "Four of a Kind";
				break;
			} else if (findPairs(valInt) == 3) {
				res = "Three of a Kind";
				break;
			} else if (findPairs(valInt) == 2) {
				res = "Two Pair";
				break;
			} else if (findPairs(valInt) == 1) {
				res = "One Pair";
				break;
			} else {
				res = "junk";
				break;
			}
		}
			return res;
	}
	/*
	 * Used in finding a flush and a straight flush
	 */
	public boolean isFlush(String[] suits) {
		if (sameSuit(suits) == false) {
			return false;
		}
		return true;
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

	public int findPairs(int[] values){
		boolean fourOfKind, threeOfKind, twoPair, onePair;
		int fH = 5, fK = 4, tK = 3, tP = 2, oP = 1, n = 0;

		fourOfKind = values[0] == values[1] &&
				values[1] == values[2] &&
				values[2] == values[3] ||
				values[1] == values[2] &&
				values[2] == values[3] &&
				values[3] == values[4];

		threeOfKind = values[0] == values[1] &&
				values[1] == values[2] ||
				values[1] == values[2] &&
				values[2] == values[3] ||
				values[2] == values[3] &&
				values[3] == values[4];

		twoPair = values[0] == values[1] &&
				values[2] == values[3] ||
				values[1] == values[2] &&
				values[3] == values[4] ||
				values[0] == values[1] &&
				values[3] == values[4];

		onePair = values[0] == values[1] ||
				values[1] == values[2] ||
				values[2] == values[3] ||
				values[3] == values[4];

		if (fourOfKind) {
			return fK;
		}
		else if (threeOfKind && !twoPair){
			return tK;
		}
		else if (threeOfKind && twoPair){
			return fH;
		}
		else if (twoPair){
			return tP;
		}
		else if (onePair){
			return oP;
		}
		else {
			return n;
		}
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
		boolean isSame = a[0] == a[1] &&
						 a[1] == a[2] &&
						 a[2] == a[3] &&
						 a[3] == a[4];

		return isSame;

	}

}