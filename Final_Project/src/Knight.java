public class Knight extends MOB {
    protected int xp;
    protected final int id;
    public Fortune fortune;

    public Knight (int id, String name, int hp, int armor, int hitModifier, DiceType damageDie, int xp) {
        super(name, hp, armor, hitModifier, damageDie);
        this.id = id;
        this.xp = xp;
    }


    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public DiceType getDamageDie(){
        return damageDie;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    public int getXP() {
        return xp;
    }

    public Fortune getActiveFortune() {
        System.out.println("gAF");
        return fortune;
    }

    public void setActiveFortune(Fortune activeFortune){
        System.out.println("setActiveFortune");
        fortune.armor = activeFortune.armor;
        fortune.name = activeFortune.name;
        fortune.hitModifier = activeFortune.hitModifier;
        fortune.hpBonus = activeFortune.hpBonus;
        fortune.type = activeFortune.type;
        //this.fortune = new Fortune(activeFortune.name, this.maxHP + activeFortune.getMaxHP(), armor + activeFortune.getArmor(), hitModifier + fortune.hitModifier, fortune.getDamageDie());
    }

    public void addXP(int xp) {
        this.xp += xp;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString(){
        String format = String.format("+============================+\n" +
                "| %-27s|\n" +
                "| id: %-23d|\n" +
                "|                            |\n" +
                "| Health: %-8dXP: %-7d|\n" +
                "|  Power: %-8sArmor: %-4d|\n" +
                "|                            |\n" +
                "+============================+\n", name, id, maxHP, getXP(), damageDie, armor);
        return format;
    }
    public String toCSV(){
        return String.format("%s,%d,%d,%d,%s,%d", name, maxHP, armor, hitModifier, damageDie, getXP());
    }
}
