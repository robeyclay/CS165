import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GameData {
    protected static final Random random = new Random();
    protected final List<Fortune> fortunes = new ArrayList();
    protected final List<MOB> monsters = new ArrayList();
    protected final List<Knight> knights = new ArrayList();
    protected final List<Knight> activeKnights = new ArrayList();

    public GameData(){

    }
    public java.util.List<Knight> getKnights(){
        return knights;
    }

    public java.util.List<Knight> getActiveKnights(){
        return activeKnights;
    }
    public Knight findKnight(String nameOrId, List<Knight> list) {
        System.out.println("findKnight");
        //System.out.println("Knights:" + knights);
        //System.out.println("ActiveKnights" + activeKnights.size());
        for (Knight checker : list) {
            if (checker == null) break;

            try {
                if (checker.getId() == Integer.parseInt(nameOrId)) {
                    return checker;
                }
            } catch (Exception e) {
                if (checker.getName().equalsIgnoreCase(nameOrId)) {
                    return checker;
                }
            }
        }
        return null;
    }

    public Knight getActive(String nameOrId){
        System.out.println("getActive:");
        System.out.println("getActive: " + activeKnights.size());
        return findKnight(nameOrId, activeKnights);
    }

    public Knight getKnight(String nameOrId){
        System.out.println("getKnight");
        return findKnight(nameOrId, knights);
    }

    public boolean setActive(Knight kt){
        System.out.println("setActive");
        if(activeKnights.size() < 4) {
            activeKnights.add(kt);
            return true;
        } else {
            return false;
        }
    }

    public void removeActive(Knight kt) {
        System.out.println("removeActive");
        activeKnights.remove(kt);
        kt.resetDamage();
    }

    public Fortune getRandomFortune(){
        return fortunes.get(random.nextInt(fortunes.size()));
    }

    public List<MOB> getRandomMonsters(){
        ArrayList<MOB> list = new ArrayList<>();
        if(monsters.size() <= activeKnights.size()){
            list.add(monsters.get(random.nextInt(monsters.size())));
        }
        return list;
    }

    public List<MOB> getRandomMonsters(int number){
        ArrayList<MOB> list = new ArrayList<>(number);
        list.add(monsters.get(random.nextInt(monsters.size())));
        return list;
    }

//    public void initKnights()
//    {
//        for (int i = 0; i < 5; i++)
//        {
//            setActive(knights.get(random.nextInt(knights.size() - 1)));
//        }
//    }

    public abstract void save(String filename);
}
