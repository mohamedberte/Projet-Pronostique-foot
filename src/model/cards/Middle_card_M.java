package model.cards;

import model.team.Player_M;

public class Middle_card_M extends Card_M {
    // Maximum card per player
    public static int MAX_MIDDLE = 100;

    public Middle_card_M(Player_M player ){
        if(player.getL(1) < MAX_MIDDLE){
            this.type = "PEU RARE";
            player.setL(1, player.getL(1) + 1); // Increase + 1 nb card common created
            this.player = player;
            this.id = this.player.getL(1);
            this.price = 500; // Prix par défaut (DOGE)
        }
        else System.out.println("Nombre de cartes Peu-rare du joueur atteint");
    }

    public double factor_rarity(){
        return (0.05 * this.player.getCapacity()) + this.player.getCapacity();
    }

}
