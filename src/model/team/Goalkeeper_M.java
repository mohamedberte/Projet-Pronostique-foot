package model.team;

public class Goalkeeper_M extends Player_M {

    public Goalkeeper_M(String name,String position){
        super(name, position);
    }

    public boolean isPlayer(){
        return false;
    }
}
