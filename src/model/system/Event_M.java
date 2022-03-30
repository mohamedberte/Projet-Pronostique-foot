package model.system;

import java.util.ArrayList;
import java.util.List;

import model.team.Player_M;

public class Event_M {
    // Evenement aves un ensemble de matchs
    int week;
    List<Game_M> matchs = new ArrayList<>();

    public Event_M(int week){
       this.week = week;
    }

    // getter and setter

    public void setMatchs(List<Game_M> matchs) {
        this.matchs = matchs;
    }

    public int getWeek(){
        return week;
    }

    public List<Game_M> getMatchs(){
        return this.matchs;
    }

    // Obtenir le resumé des joueurs
    public void Summary(int choice){
        Game_M game = matchs.get(choice);
        if(game.getTeam1().getName().equals("FC Nantes")){
            System.out.println("*******************************************");
            System.out.println("Compétence des joueurs de "+ game.getTeam1().getName() +" en fin de match SEM ("+ this.getWeek() +")");
            System.out.println("-------------------------------------------");
            int i = 1;
            for( Player_M p : game.getTeam1().getPlayers() ){
                System.out.println("" + i + "- " + p.getName() +", Poste : " + p.getPosition() + ", Note : "+ p.getCapacity());
                i = i + 1;
            }
        }
        else{
            System.out.println("*******************************************");
            System.out.println("Compétence des joueurs de "+ game.getTeam1().getName() +" en fin de match SEM ("+ this.getWeek() +")");
            System.out.println("-------------------------------------------");
            int i = 1;
            for( Player_M p : game.getTeam1().getPlayers() ){
                System.out.println("" + i + "- " + p.getName() +", Poste : " + p.getPosition() + ", Note : "+ p.getCapacity());
                i = i + 1;
            }
        }
    }
}
