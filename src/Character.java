public class Character {
    private static int baseSwordDmg;
    private static int baseShieldDef;
    private static double baseSPD;

    String name;
    int level;
    double maxHP;
    // maxHP = 100+(10*level);
    double currentHP ;
    double maxMana;
    double currentMana;
    double maxSPD;
    double currentSPD;
    int isSwordEquip = 0;
    int isShieldEquip = 0;
    int swordLevel =1;
    int shieldLevel =1;
    double dmg;
    double def;
    boolean isUnconscious;


    Character(String name, int level){
        this.name = name;
        this.level = level;
        maxHP = 100+(10*level);
        currentHP = maxHP;
        maxMana = 50+(2*level);
        currentMana = maxMana;
        baseSPD = 10;
        maxSPD = baseSPD*(0.1+0.03*level);
        baseSwordDmg = 10;
        baseShieldDef = 10;
        isUnconscious = false;
    }

    private void updateStatus(){
        maxHP = 100+(10*level);
        maxMana = 50+(2*level);
        maxSPD = baseSPD*(0.1+0.03*level);
        currentSPD = maxSPD - (isSwordEquip*baseSPD*(0.1+0.04*swordLevel)) - (isShieldEquip*baseSPD*(0.1+0.08*level));
        dmg = isSwordEquip*baseSwordDmg*(1+0.1*swordLevel);
        def = isShieldEquip*baseShieldDef*(1+0.05*shieldLevel);
    }
    public void characterLevelAscending(){
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

    public double attack(double opponentDef) {
        if(!isUnconscious) {
            if (currentMana != 0) {
                currentMana--;
                return dmg - opponentDef;
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
}
