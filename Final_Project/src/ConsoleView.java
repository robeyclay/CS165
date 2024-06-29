import java.util.List;
import java.util.Scanner;

public class ConsoleView implements GameView {
    private final Scanner scanner = new Scanner(System.in);
    public ConsoleView(){}

    @Override
    public void splashScreen(){
        display("Welcome");
    }
    @Override
    public void endGame(){
        display("Goodbye");
    }

    @Override
    public boolean checkContinue() {
        display("Would you like to continue on your quest (y/n)? ");
        boolean check;
        check = scanner.next().equalsIgnoreCase("y");
        return check;
    }

    @Override
    public String displayMainMenu() {
        return "What would you like to do?";
    }

    @Override
    public void printHelp() {
        display(" Unsure what to do, here are some options:\n" +
                "            ls or list all  - listing the knights\n" +
                "            list active  - list the active knights knights only\n" +
                "            show name or id - show the knight details card\n" +
                "            set active name or id - set knight as active (note: only 4 knights can be active) \n" +
                "            remove active name or id - remove a knight from active status (heals knight)\n" +
                "            explore or adventure or quest - find random monsters to fight\n" +
                "            save filename - save the game to the file name (default: saveData.csv)\n" +
                "            exit or goodbye - to leave the game\n" +
                "\n" +
                " Game rules: You can have four active knights. As long as they are active, they won't heal, \n" +
                " but they can gain XP by going on adventures.\n" +
                " When you make a knight inactive, they will heal. How many monsters can you defeat \n" +
                " before, you have to heal?");

    }

    @Override
    public void knightNotFound() {
        display("Knight not found!");
    }

    @Override
    public void listKnights(List<Knight> knights) {
        for(Knight checker: knights){
            display(""+ checker.getId() + ": " + checker.getName());
        }
    }

    @Override
    public void showKnight(Knight knight) {
        display(knight.toString());
    }

    @Override
    public void printBattleText(List<MOB> monsters, List<Knight> activeKnights) {
        StringBuilder combine = new StringBuilder();
        for(int i = 0; i < activeKnights.size(); i++){
            Knight tempKnight = activeKnights.get(i);
            MOB tempMonster = monsters.get(i);
            combine.append(String.format("%28s%s\n", tempKnight.getName(), tempMonster.getName()));
        }
        display("Our heroes come across the following monsters. Prepare for battle!\n" +
                "Knights                     Foes\n" + combine.toString());
    }

    @Override
    public void printBattleText(MOB dead) {
        display(dead.getName() + "was defeated!");
    }

    @Override
    public void printFortunes(List<Knight> activeKnights) {
        display("For this quest, our knights drew the following fortunes!");
        for(Knight checker: activeKnights){
            display(checker.getName() + "drew" + checker.getActiveFortune());
        }
    }

    @Override
    public void setActiveFailed() {
        display("Unable to set active knight. Only four can be active at a time.");
    }

    @Override
    public void printDefeated() {
        display("All active knights have been defeated!");
    }

    public void display(String str){
        System.out.println(str);
    }
}
