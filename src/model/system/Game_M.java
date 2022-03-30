package model.system;

import model.team.Team_M;

public class Game_M {
    private Team_M team1;
    private Team_M team2;


    public Team_M getTeam1() {
        return team1;
    }

    public Team_M getTeam2() {
        return team2;
    }
    public void setTeam1(Team_M team1) {
        this.team1 = team1;
    }
    public void setTeam2(Team_M team2) {
        this.team2 = team2;
    }
}
