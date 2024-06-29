import java.util.Random;

public class DiceSet {
    Random randGen = new Random();
    public int roll(DiceType roll) {
        switch(roll) {
            case D4:
                return randGen.nextInt(4) + 1;
            case D6:
                return randGen.nextInt(6) + 1;
            case D8:
                return randGen.nextInt(8) + 1;
            case D10:
                return randGen.nextInt(10) + 1;
            case D12:
                return randGen.nextInt(12) + 1;
            case D20:
                return randGen.nextInt(20) + 1;
            default:
                return -1;
        }
    }
}
