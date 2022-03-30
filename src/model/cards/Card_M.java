package model.cards;

import java.util.Objects;

import model.team.Player_M;
import model.user.User_M;

public abstract class Card_M{
    protected int id;
    protected String type;
    protected Player_M player;
    protected int price;
    protected User_M user;

    protected boolean ordered = false;

    // Getter and setter functions
	public Player_M getPlayer() {
		return this.player;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return this.price;
	}

	public User_M getUser() {
        return this.user;
	}
	public String getType(){
		return this.type;
	}
	

	public boolean IsOrdered() {
		return this.ordered;
	}

	public void setType(String type){
		this.type = type;
	}

	public void setPlayer(Player_M player) {
        this.player = player;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setUser(User_M user) {
        this.user = user;
	}

	public void SetOrdered() {
        if (this.ordered) this.ordered = false;
        else this.ordered = true;
	}

	public abstract double factor_rarity();

    // Redefinission de méthode Object
	public boolean equals(Card_M cards){
		if(this == cards) return true;
		if (cards == null|| getClass() != cards.getClass()) return false;
		return (id == cards.id && type.equals(cards.getType()) && player.getName().equals(cards.getPlayer().getName()));
	}

	@Override
    public int hashCode() {
        return Objects.hash(id, type, player);
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