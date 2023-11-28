public class shield {
    private String name;
    double def;
    private int level = 1;
    private int currentExp;
    private int maxExp;
    double decreaseSPD;
    shield(String name,int def){
        this.name = name;
        this.def = def;
        this.decreaseSPD = 0.1+0.08*level;
    }
    private void updateStatus(){
        def = 1+0.05*level;
        decreaseSPD = 0.1+0.08*level;
        maxExp = 10*level;
    }
    public void shieldLevelAscending(){
        level++;
        updateStatus();
    }
    public void updateExp(int exp){
        this.currentExp += exp;
        if(currentExp >= maxExp){
            currentExp -= maxExp;
            this.shieldLevelAscending();
            System.out.println("Level up !!");
        }
    }
    public void showStatus(){
        System.out.println("Shield: " + this.name);
        System.out.println("Defense : " + this.def);
        System.out.println("Level : "+this.level + "exp" + currentExp + " / " + maxExp);
    }
}
