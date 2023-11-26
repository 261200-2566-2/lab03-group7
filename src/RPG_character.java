public class RPG_character {
    private double HP;
    private double mana;
    private double LV;
    private double defense;
    private double attack;
    private double speed;

    RPG_character(double LV,sword a,shield d){
        this.HP = 100+10*LV;
        this.mana = 50+2*LV;
        this.speed = 20*(1+0.03*LV);
        this.defense = d.defense*(1+0.05*LV);
        this.attack = a.attack*(1+0.1*LV);
        this.LV=LV;
    }
    public void damage(double damage){
        this.HP = this.HP-(damage-defense);
    }
    public double attack(){
        return this.attack;
    }

}
