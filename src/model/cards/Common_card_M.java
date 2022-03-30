package model.cards;

import model.team.Player_M;

public class Common_card_M extends Card_M{
    // Maximum card per player
    public static int MAX_COMMON = 1000;

    public Common_card_M(Player_M player ){
        if(player.getL(0) < MAX_COMMON){
            this.type = "COMMUNE";
            player.setL(0, player.getL(0) + 1); // Increase + 1 nb card common created
            this.player = player;
            this.id = this.player.getL(0);
            this.price = 200; // Prix par défaut (DOGE)
        }
        else System.out.println("Nombre de cartes commune du joueur atteint");
        
    }

    public double factor_rarity(){
        return (0 * this.player.getCapacity()) + this.player.getCapacity();
    }
}