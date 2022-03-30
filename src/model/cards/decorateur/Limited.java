package model.cards.decorateur;

import model.cards.Card_M;

public class Limited extends Special_card{
    // Type limited
    public Limited(Card_M card ){
        this.card = card;
        this.card.setPrice(this.getPrice() + 1000);
        this.card.setType("LIMITED " + this.card.getType());
    }

    @Override
    public double factor_rarity() {
        // add 10% on card factor
        return (this.card.factor_rarity() * 0.15 ) + this.card.factor_rarity() ;
    }
}
