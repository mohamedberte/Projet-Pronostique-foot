package model.visitor;

import model.cards.Card_M;
import model.user.Team_user_M;

public class ScoreCalculator extends Visitor{

    /**
     * Visiteur qui recupère les cartes de l'utilisateur pour le calcule du score
     * @param team_user l'équipe de l'utilisateur
     * @return v : la valeur du score
     */
    @Override
    public Double visit(Team_user_M team_user) {
        double v = 0;
        for(Card_M c  : team_user.getCards()){
            v = v + c.factor_rarity();
        }
        return v;
    }

}
