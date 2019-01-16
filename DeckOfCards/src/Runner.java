
public class Runner {

	public static void main(String[] args) {
		int count = 0;
		while (count < 1000) {
			Hand nH = new Hand();
			nH.printHand();
			count++;
		}
	}

}
