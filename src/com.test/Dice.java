public class Dice {
    int totalNumbers; // starting from 1


    public int getNumber() {
        return (int)Math.ceil( Math.random() * (double) totalNumbers);
    }

    public Dice(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }
}
