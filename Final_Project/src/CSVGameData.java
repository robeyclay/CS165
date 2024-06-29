import java.io.*;
import java.util.Scanner;

public class CSVGameData extends GameData{
    int id = 1;

    public CSVGameData(String gameData, String saveData) {
        loadGameData(gameData);
        loadSaveData(saveData);
        applyCashMoney();
    }

    public void applyCashMoney()
    {
        for (Knight k : knights)
        {
            k.fortune = getRandomFortune();
        }
    }

    void loadGameData(String gameData){
        System.out.println("loadGameData");
        try {
            Scanner scnr = new Scanner(new File(gameData));
            String line = scnr.nextLine();

            while (scnr.hasNextLine())
            {
                String[] list = line.split(",");
                gameDataHelper(list);
                line = scnr.nextLine();
            }
            String[] listAfterWhile = line.split(",");
            gameDataHelper(line.split(","));
            scnr.close();
        } catch (Exception e) {
            System.out.println("loadGameData: " + e);
        }

    }

    public void gameDataHelper(String[] list) {
        System.out.println("gameDatahelper");
        if (list[0].equalsIgnoreCase("mob")) {
            String name = list[1];
            int hp = Integer.parseInt(list[2]);
            int armor = Integer.parseInt(list[3]);
            int hitModifier = Integer.parseInt(list[4]);
            DiceType dice = DiceType.valueOf(list[5]);
            monsters.add(new MOB(name, hp, armor, hitModifier, dice));
        }
        if (list[0].equalsIgnoreCase("fortune")) {
            String name = list[1];
            int hp = Integer.parseInt(list[2]);
            int armor = Integer.parseInt(list[3]);
            int hitModifier = Integer.parseInt(list[4]);
            if (list[5] != null && list[5].contains("D")) {
                DiceType dice = DiceType.valueOf(list[5]);
                fortunes.add(new Fortune(name, hp, armor, hitModifier, dice));
            } else {
                fortunes.add(new Fortune(name, hp, armor, hitModifier));
            }
        }
    }

    void loadSaveData(String saveData){
        System.out.println("loadSaveData Entered");
        try {
            Scanner scnr = new Scanner(new File(saveData));
            String line = scnr.nextLine();

            while (scnr.hasNextLine())
            {
                String[] list = line.split(",");
                saveDataHelper(list);
                line = scnr.nextLine();
            }
            String[] listAfterWhile = line.split(",");
            saveDataHelper(listAfterWhile);
            scnr.close();
        } catch (Exception e) {
            System.out.println("loadSaveData: " + e);
        }
    }

    public void saveDataHelper(String[] list){   //knight init
        String name = list[0];
        int hp = Integer.parseInt(list[1]);
        int armor = Integer.parseInt(list[2]);
        int hitModifier = Integer.parseInt(list[3]);
        DiceType dice = DiceType.valueOf(list[4]);
        int xp = Integer.parseInt(list[5]);
        knights.add(new Knight(id, name, hp, armor, hitModifier, dice, xp));
        System.out.println("Save Data Knight Added: ID " + id);
        id++;
    }

    @Override
    public void save(String filename) {
        System.out.println("Save");
        try (PrintWriter output = new PrintWriter(filename)) {

            StringBuilder string = new StringBuilder();
            for( Knight checker: knights){
                string.append(checker.toCSV());
                string.append('\n');
            }
            output.print(string.toString());

        } catch (Exception e) {
            //System.out.println("save:" + e);
        }
    }
}
