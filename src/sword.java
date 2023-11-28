public class sword {
    private String name;
    private int level = 1;
    private double atk;
    private double decreaseSPD;
    private int currentExp;
    private int maxExp;

    sword(String name,int atk){
        this.name = name;
        this.atk = atk;
        this.decreaseSPD = 0.1+0.04*level;
    }

    public double getAtk() {
        return atk;
    }
    public double getDecreaseSPD(){
        return decreaseSPD;
    }
    private void updateStatus(){
        atk = 1+0.1*level;
        decreaseSPD = 0.1+0.04*level;
        maxExp = 10*level;
    }
    public void swordLevelAscending(){
        level++;
        updateStatus();
    }
    public void updateExp(int exp){
        this.currentExp += exp;
        if(currentExp >= maxExp){
            currentExp -= maxExp;
            this.swordLevelAscending();
            System.out.println("Level up !!");
        }
    }
    public void showStatus(){
        System.out.println("Sword : "+this.name);
        System.out.println("Attack : "+this.atk );
        System.out.println("Level : "+this.level + "exp" + currentExp + " / " + maxExp);
    }
}
