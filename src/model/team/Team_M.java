package model.team;

import java.util.ArrayList;
import java.util.List;

public class Team_M {
    // Une équipe : nom, joueurs, gardien
    private String name;
    private List<Player_M> players = new ArrayList<>();

    public Team_M(String name, List<Player_M> players){
        this.name = name;
        this.players = players;
    }

    public Team_M(String name){
        this.name = name;
    }

    public Team_M(){}

    // Getter
    public String getName(){
        return this.name;
    }
    public List<Player_M> getPlayers(){
        return this.players;
    }
    
    // Setter
    public void setName(String name){
        this.name = name;
    }
    public void setPlayer(List<Player_M> players){
        this.players = players;
    }
    public void setPlayer(Player_M player){
        this.players.add(player);
    }

    public void createTeamByList(List<List<String>> players){
        for(List<String> p : players){
            Player_M player;
            if(p.get(1).equals("G")) player = new Goalkeeper_M(p.get(0), p.get(1)); // Si Gardien on crée
            else player = new Field_player_M(p.get(0), p.get(1));
            player.setCapacity(Double.parseDouble(p.get(2)));
            
            this.setPlayer(player);
        }
    }

    // Redefinition des méthodes Objects
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
		s.append("************************");
		s.append("\n");
		s.append("          Equipe        ");
		s.append("\n");
		s.append("************************");
		s.append("\n");
		s.append("- Nom : " + this.name);
		
        return s.toString();
    }
}
