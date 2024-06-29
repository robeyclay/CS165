public class MOB implements Attributes {
    protected int armor;
    protected int damage;
    protected DiceType damageDie;
    protected int hitModifier;
    protected int maxHP;
    protected String name;

    public MOB(String name, int hp, int armor, int hitModifier, DiceType damageDie) {
        this.name = name;
        this.maxHP = hp;
        this.hitModifier = hitModifier;
        this.armor = armor;
        this.damageDie = damageDie;
        this.damage = 0;
    }

    @Override
    public int getHitModifier() {
        return hitModifier;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    public String getName(){
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void addDamage(int damage){
        this.damage += damage;
    }

    public int getHP(){
        return maxHP - damage;
    }

    public void resetDamage(){
        this.damage = 0;
    }

    public DiceType getDamageDie() {
        return damageDie;
    }

    public String toString() {
        String format = String.format("+======================+\n" +
                "|Name:%17s|\n" +
                "|HP:%19d|\n" +
                "|Armor:%16d|\n" +
                "|Weapon Skill:%9d|\n" +
                "|Power:%16s|\n" +
                "+======================+\n", name, getDamage(), armor, hitModifier, damageDie);
        return format;
    }

//    public MOB copy() {
//        MOB copy = new MOB(name, maxHP, armor, hitModifier, damageDie);
//        return copy;
//    }
}
