package model.team;

public class Field_player_M extends Player_M {

    public Field_player_M(String name, String position){
        super(name, position);
    }
    
    public boolean isPlayer(){
        return true;
    }
}
