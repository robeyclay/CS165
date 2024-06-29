import java.util.List;
import java.util.Random;

public class CombatEngine {
    public GameData data;
    public GameView view;
    public Random random = new Random();

    public CombatEngine(GameData data, GameView view) {
        this.view = view;
        this.data = data;
    }
    public void initialize(){
        System.out.println("initialize");
        for(Knight checker: data.activeKnights){
            Fortune randFortune = data.getRandomFortune();
            checker.setActiveFortune(randFortune);
        }
        view.printFortunes(data.activeKnights);
    }

    public void runCombat() {
        System.out.println("runCombat");


        List<MOB> monsters = data.getRandomMonsters();
        //view.printBattleText(monsters, data.activeKnights);   POTENTIAL NULL
        DiceSet dice = new DiceSet();
        int XP = 0;

        if (data.activeKnights.size() > 0 && monsters.size() > 0) {
            for (Knight checker : data.activeKnights) {
                int hitRoll = dice.roll(DiceType.D20);
                MOB randMonster = monsters.get(random.nextInt(monsters.size()));
                if (checker.hitModifier + hitRoll > randMonster.armor) {
                    System.out.println("Hit!");
                    int damageToMonster = dice.roll(checker.getDamageDie());
                    randMonster.addDamage(damageToMonster);
                    if (randMonster.getHP() <= 0) {
                        view.printBattleText(randMonster);
                        monsters.remove(randMonster);
                        XP++;
                    }
                } else {
                    System.out.println("Miss!");
                }
            }
            for (MOB checker: monsters){
                int hitRoll = dice.roll(DiceType.D20);
                Knight randKnight = data.activeKnights.get(random.nextInt(data.activeKnights.size()));
                if(checker.hitModifier + hitRoll > randKnight.armor) {
                    System.out.println("Hit!");
                    int damageToKnight = dice.roll(checker.getDamageDie());
                    randKnight.addDamage(damageToKnight);
                    if (randKnight.getHP() <= 0) {
                        view.printBattleText(randKnight);
                        data.activeKnights.remove(randKnight);
                    }
                }
            }
        } else if(monsters.size() == 0){
            System.out.println("Victory!");
            view.checkContinue();
            for(Knight checker: data.activeKnights){
                checker.addXP(XP);
            }
        } else if(data.activeKnights.size() == 0){
            view.printDefeated();
        }
    }

    public void clear(){
        System.out.println("clear");
//        for(Knight checker : data.getKnights()){
//            checker.setActiveFortune(null);
//        }
    }
}
