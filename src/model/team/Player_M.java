package model.team;

import java.util.Objects;

public abstract class Player_M {
    private String name;
    private String position;
    private Double capacity;

    private int[] nb_card_created;  // [i, j, k] i : nb Common, j : nb Middle Rare, k : nb Rare 

    public Player_M(String name, String position) {
        this.name = name;
        this.position = position;
        this.capacity = 0.0;
        this.nb_card_created = new int[3];
    }

    // Setter and Getter
    public String getName() {
        return name;
    }

    public void setL(int i, int val ) {
        this.nb_card_created[i] = val;
    }

    public int getL(int i) {
        return this.nb_card_created[i];
    }

    public int[] getL() {
        return this.nb_card_created;
    }

    public Double getCapacity() {
        return capacity;
    }
    

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    // Redefinission de méthode Object
	public boolean equals(Player_M player){
		if(this == player) return true;
		if (player == null|| getClass() != player.getClass()) return false;
		return (this.name.equals(player.name));
	}

	@Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    public abstract boolean isPlayer();
}
