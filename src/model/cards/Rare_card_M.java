package model.cards;

import model.team.Player_M;

public class Rare_card_M extends Card_M {

    // Maximum card per player
    public static int MAX_RARE = 10;

    public Rare_card_M(Player_M player ){
        this.type = "RARE";
        this.player = player;
        


        if(player.getL(0) < MAX_RARE){
            this.type = "RARE";
            player.setL(2, player.getL(2) + 1); // Increase + 1 nb card rare created
            this.player = player;
            this.id = this.player.getL(2);
            this.price = 1000; // Prix par défaut (DOGE)
        }
        else System.out.println("Nombre de cartes rare du joueur atteint");
    }

    public double factor_rarity(){
        return (0.10 * this.player.getCapacity()) + this.player.getCapacity();
    }
}
