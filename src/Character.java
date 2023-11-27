public class Character {
    private static int baseSwordDmg;
    private static int baseShieldDef;
    private static double baseSPD;
    private String name;
    private int level = 1;
    private double maxHP;
    // maxHP = 100+(10*level);
    private double currentHP ;
    private double maxMana;
    private double currentMana;
    private double SPD;
    private int isSwordEquip = 0;
    private int isShieldEquip = 0;
    private int swordLevel =1;
    private int shieldLevel =1;
    private double dmg;
    private double def;
    private boolean isUnconscious;
    private int currentExp;
    private int maxExp;

    Character(String name){
        this.name = name;
        maxHP = 100+(10*level);
        currentHP = maxHP;
        maxMana = 50+(2*level);
        currentMana = maxMana;
        baseSPD = 10;
        SPD = baseSPD*(0.1+0.03*level);
        baseSwordDmg = 10;
        baseShieldDef = 10;
        isUnconscious = false;
    }
    private void updateStatus(){
        maxHP = 100+(10*level);
        maxMana = 50+(2*level);
        maxExp = 100*level;
        SPD = (baseSPD*(0.1+0.03*level))- (isSwordEquip*baseSPD*(0.1+0.04*swordLevel)) - (isShieldEquip*baseSPD*(0.1+0.08*level));
        dmg = isSwordEquip*baseSwordDmg*(1+0.1*swordLevel);
        def = isShieldEquip*baseShieldDef*(1+0.05*shieldLevel);
    }
    private void characterLevelAscending(){
        level++;
        updateStatus();
    }
    public void swordEquip(){
        isSwordEquip = 1;
        updateStatus();
    }
    public void shieldEquip(){
        isShieldEquip = 1;
        updateStatus();
    }
    public void swordUnEquip(){
        isSwordEquip = 0;
        updateStatus();
    }
    public void shieldUnEquip(){
        isShieldEquip = 0;
        updateStatus();
    }
    public void swordLevelAscending(){
        if(isSwordEquip == 1){
            swordLevel++;
            updateStatus();
        }
        else System.out.println("You didn't equip your sword yet");
    }
    public void shieldLevelAscending(){
        if(isShieldEquip == 1){
            shieldLevel++;
            updateStatus();
        }
        else System.out.println("You didn't equip your shield yet");
    }
    public double wasAttacked(double damage){
        double damageTaken = damage - isShieldEquip*def ;
        currentHP = currentHP - damageTaken;
        if(currentHP <= 0 ){
            currentHP = 0;
            isUnconscious = true;
        }
        return damageTaken;
    }
    public double attack() {
        if(!isUnconscious) {
            if (currentMana != 0) {
                currentMana--;
                return dmg;
            }
            System.out.println("You run out of mana");
            return 0;
        }
        System.out.println("Character is unconscious");
        return 0;
    }

    public void healthPack(int blood){
        if(!isUnconscious) {
            if (currentMana >= (blood/maxHP*maxMana/3)) {
                currentMana = currentMana-(blood/maxHP*maxMana/3);
                currentHP = currentHP + blood;
            }
            System.out.println("You run out of mana");
        }
        currentHP = maxHP;
        currentMana = maxMana;
    }
    public void changeName(String n){
        this.name = n;
    }
    public void showStatus(){
        System.out.println("Name : "+this.name);
        System.out.println("Level : "+this.level + "exp" + currentExp + " / " + maxExp);
        System.out.println("HP : " + currentHP + " / " + maxHP);
        System.out.println("mana : " + currentMana + " / " + maxMana);
        System.out.println("speed : " + this.SPD);
        if(isSwordEquip == 1) System.out.println("sword level : " + swordLevel);
        if(isShieldEquip == 1) System.out.println("shield level : " + shieldLevel);
    }
    public void updateExp(int exp){
        this.currentExp += exp;
        if(currentExp >= maxExp){
            currentExp -= maxExp;
            this.characterLevelAscending();
            System.out.println("Level up !!");
        }
    }

}
