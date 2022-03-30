package model.visitor;

import model.user.Team_user_M;

// Class visiteur : liste des méthode visite
public abstract class Visitor {
    public abstract Double visit( Team_user_M user);
}
