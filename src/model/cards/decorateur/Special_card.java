package model.cards.decorateur;

import java.util.Objects;

import model.cards.Card_M;
import model.team.Player_M;
import model.user.User_M;

public abstract class Special_card extends Card_M {
    // Card decorator
    Card_M card;

    public abstract double factor_rarity();

    // Getter and setter functions
	public Player_M getPlayer() {
		return this.card.getPlayer();
	}

	public int getId() {
		return this.card.getId();
	}

	public int getPrice() {
		return this.card.getPrice();
	}

	public User_M getUser() {
        return this.card.getUser();
	}
	public String getType(){
		return this.card.getType();
	}

	public boolean IsOrdered() {
		return this.card.IsOrdered();
	}

	public void setType(String type){
		this.card.setType(type);
	}

	public void setPlayer(Player_M player) {
        this.card.setPlayer(player);
	}

	public void setPrice(int price) {
		this.card.setPrice(price);
	}

	public void setUser(User_M user) {
        this.user = user;
	}

	public void SetOrdered() {
        this.card.SetOrdered();
	}


    // Redefinission de méthode Object
	public boolean equals(Card_M cards){
		return this.card.equals(cards);
	}

	@Override
    public int hashCode() {
        return Objects.hash(this.card.getId(), this.card.getType(), this.card.getPlayer().getName());
    }

    public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("************************");
		s.append("\n");
		s.append("          Carte         ");
		s.append("\n");
		s.append("************************");
		s.append("\n");
		s.append("-Rareté : "+this.type);
		s.append("\n");
		s.append("-n° : "+ this.id);
		s.append("\n");
		s.append("-Joueur : "+this.player.getName());
		s.append("\n");
		s.append("-Position : "+this.player.getPosition());
		s.append("\n");
		s.append("-Score : "+this.factor_rarity());
		s.append("\n");
        return s.toString();
    }
}
