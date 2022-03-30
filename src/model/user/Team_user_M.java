package model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.cards.Card_M;
import model.visitor.Visitor;

public class Team_user_M {
    private List<Card_M> cards = new ArrayList<>();
    private User_M user;

    // scanner
    private static Scanner sc = new Scanner(System.in);

    
    public Team_user_M(User_M user){
        this.user = user;
    }

    public List<Card_M> getCards() {
        return cards;
    }

    /**
     * Méthode d'acceptation d'un visiteur pour visiter l'équipe du joueur
     * @param v Le visiteur correspondant
     */
    public Double accept(Visitor v){
        return v.visit(this);
    }

    /**
     * Ajout d'une carte dans l'équipe du joueur et vérifiant quelques conditions
     * @param card la carte du joueur (Type Card_M)
     * @return boolean if add respect some condition
     */
    private boolean addCardP(Card_M card){
        if(this.cards.size() > 3){
            System.out.println("Equipe déjà pleine");
            return false;
        }
        else{
            // Existance du joueur parmis les cartes
            for(Card_M c : this.cards){
                if(c.getPlayer().getName().equals(card.getPlayer().getName())){
                    System.out.println("Une carte de "+c.getPlayer().getName()+" existe déjà.");
                    return false;
                }
            }
        }

        // Contrainte : être constitué d'un gardien
        if( ! goalKeeperExist() && this.cards.size() == 3 && card.getPlayer().isPlayer()){
            System.out.println("Votre équipe possède déjà 3 joueurs, il vous faut un gardien.");
            return false;
        }
        if( goalKeeperExist() && ! card.getPlayer().isPlayer() ){
            System.out.println("Votre équipe possède déjà  1 gardien, il vous faut un joueur.");
            return false;
        }

        // Ajout
        card.SetOrdered(); // Carte commandé pour l'équipe
        this.cards.add(card);
        return true;
    }

    /**
     * Verifie si un gardien existe dans l'équipe
     * @return false ou true
     */
    private boolean goalKeeperExist(){
        for( Card_M c : this.cards){
            if( ! c.getPlayer().isPlayer()) return true;
        }
        return false;
    }

    /**
     * Verifie si un gardien existe pour une liste de cartes données
     * @param cards Liste de cartes
     * @return false or true 
     */
    private boolean goalKeeperExist(List<Card_M> cards){
        for( Card_M c : cards){
            if( ! c.getPlayer().isPlayer()) return true;
        }
        return false;
    }

    /**
     * Creation d'équipe du joueur;
     * Certaine vérification sont effectuée pour s'assurer que l'insertion respecte les règles
     */
    protected void showTeam(){
        // Afficher les informations des carte
        System.out.println("Mon équipe :");
        System.out.println("Il y'a "+ this.cards.size() + " carte(s) disponible dans mon équipe");
        int i = 0;
        for( Card_M c : this.cards){
            i++;
            if(c.getPlayer().isPlayer()){
                System.out.println( i +"- Joueur : "+ c.getPlayer().getName() +", Poste =  "+ c.getPlayer().getPosition() + ", n° =  "+c.getId() +", Type : "+ c.getType() + ", Prix :"+ c.getPrice());
            }
            else{
                System.out.println( i +"- Gardien : "+ c.getPlayer().getName() +", Poste =  "+ c.getPlayer().getPosition() + ", n° =  "+c.getId() +", Type : "+ c.getType() + ", Prix :"+ c.getPrice());
            }
        }
    }
    /**
     * Creation d'équipe du joueur;
     * Certaine vérification sont effectuée pour s'assurer que l'insertion respecte les règles
     */
    protected void createTeam(){
        if(this.user.getCard() == null){ System.out.println("Vous n'avez aucune carte. Faites un tour au marché.");  return;}
        if(this.user.getCard().size() < 4){
            System.out.println("Votre nombre de carte est inférieur pour pouvoir constituer une équipe.");
            return;
        }

        if(! this.goalKeeperExist(this.user.getCard())){
            System.out.println("Vous ne disposez pas de carte gardien.");
            return;
        }

        this.user.CardList();

        // Comptabilise le nombre d'insertion possible
        int nb = 4 - this.cards.size();

        do {
            System.out.println("Faites un choix.");

            int num = Integer.parseInt(sc.nextLine());
            if( num < 1 && num > this.user.getCard().size()) {
                System.out.println("Vous avez fait le mauvais choix");
            }
            else{
                if(this.addCardP(this.user.getCard().get(num - 1))) nb--;;
                if(nb != 0 ) System.out.println("Il reste " + nb + " place(s)");
                else System.out.println("Fin de la constitution d'équipe");
            }
            
            } while (nb > 0);
        
    }
}
