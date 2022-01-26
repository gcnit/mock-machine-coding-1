import java.util.Random;
public class Dice {
	private int number;
	public Dice(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}
	public int RollDie() {
		Random r = new Random();
		return r.nextInt((number - 1) + 1) + 1;
	}
}