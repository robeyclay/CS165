import java.sql.SQLOutput;

/**
 *
 * Welcome to Knight Fight. A game that helps knights
 * of the round table fight enemies and maybe find the holy grail
 *
 * Students  do  not need to  edit this file.
 *
 * @author Albert Lionelle <br>
 *         lionelle@colostate.edu <br>
 *         Computer Science Department <br>
 *         Colorado State University
 * @version 202010
 */
public class Main {
    public Main(){}
    /**
     * Program start point. Pass in debug  on the command line to have it trigger tests instead of the game
     */
    private static String gamedata = "gamedata.csv";
    private static String saveData = "knights.csv";

    public static void main(String[] args) {
          //  UnitTests tests = new UnitTests();
        //tests.runTests();

        GameData data = new CSVGameData(gamedata, saveData);
        GameView view  = new ConsoleView();
        CombatEngine engine = new CombatEngine(data, view);
        GameController controller = new GameController(data, view, engine);
        controller.start();

    }
}
