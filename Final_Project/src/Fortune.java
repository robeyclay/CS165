public class Fortune implements Attributes{
    public String name;
    public int hpBonus;
    public int armor;
    public int hitModifier;
    public DiceType type;

    public Fortune(String name, int hpBonus, int armor, int hitModifier){
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
    }
    public Fortune (String name, int hpBonus, int armor, int hitModifier, DiceType type) {
        this.name = name;
        this.hpBonus = hpBonus;
        this.armor = armor;
        this.hitModifier = hitModifier;
        this.type = type;
    }
    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return hpBonus;
    }

    @Override
    public DiceType getDamageDie() {
        return type;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
      String format = String.format("+======================+\n" +
                "|%-22s|\n" +
                "|HP Bonus:%+13d|\n" +
                "|AC Bonus:%+13d|\n" +
                "|Hit Bonus:%+12d|\n" +
                "|Damage Adj:%11s|\n" +
                "+======================+\n", name, hpBonus, armor, hitModifier, type == null ? "-" : type);
      return format;
    }
}
