package model.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.cards.Card_M;
import model.system.System_M;

public class User_M {
    private String name;
    private String password;
    private int piece;
    private Team_user_M team_now = new Team_user_M(this);
    private Team_user_M team_next = new Team_user_M(this);
    List<Card_M> my_cards;
    
    // System
    private System_M sys = null;
    // scanner
    public static Scanner sc = new Scanner(System.in);

    // Les constructeurs
    public User_M(String name, String pass){
        this.name = name;
        this.password = pass;
        this.piece = 10000; // debute avec 10000 pièce
        this.my_cards = new ArrayList<>();
    }

    public User_M(String name, String pass, int piece){
        this.name = name;
        this.password = pass;
        this.piece = piece; // debute avec 10000 pièce
        this.my_cards = new ArrayList<>();
    }

    public User_M(String name, String pass, int piece, List<Card_M> l){
        this.name = name;
        this.password = pass;
        this.piece = piece; // debute avec 10000 pièce
        this.my_cards = l;
    }

    // Getter

    public List<Card_M> getMy_cards() {
        return my_cards;
    }
    public String getPassword() {
        return password;
    }
    public Team_user_M getTeam_next() {
        return team_next;
    }
    public Team_user_M getTeam_now() {
        return team_now;
    }
    public String getName(){
        return this.name;
    }
    public int getPiece(){
        return this.piece;
    }
    public System_M getSys(){
        return this.sys;
    }
    public List<Card_M> getCard(){
        return this.my_cards;
    }

    // Setter
    public void setMy_cards(List<Card_M> my_cards) {
        this.my_cards = my_cards;
    }
    public void setCard(Card_M my_cards) {
        this.my_cards.add(my_cards);
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTeam_next(Team_user_M team_next) {
        this.team_next = team_next;
    }
    public void setTeam_now(Team_user_M team_now) {
        this.team_now = team_now;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPiece(int piece){
        this.piece = piece;
    }
    public void setSys(System_M sys){
        this.sys = sys;
    }

    // S'inscrire à un match
    public boolean register(){
        return false;
    }

    // Vendre les cartes
    public boolean sellCard(){
        System.out.println("************************");
        for(Card_M m : this.my_cards){
            System.out.println(m);
        }
        return true;
    }
        
    // Choix de carte
    public void infoCard(Card_M card){
        // Afficher les informations des carte
        for( Card_M c : this.my_cards){
            c.toString();
        }
    }

    public void CardList(){
        // Afficher les informations des carte
        System.out.println("Liste de mes cartes");
        System.out.println("Vous avez "+ this.my_cards.size() + " carte(s)");
        int i = 0;
        for( Card_M c : this.my_cards){
            i++;
            if(c.getPlayer().isPlayer()){
                System.out.println( i +"- Joueur : "+ c.getPlayer().getName()+ ", Poste =  "+ c.getPlayer().getPosition() +", n° =  "+c.getId() +", Type : "+ c.getType() + ", Prix :"+ c.getPrice());
            }
            else{
                System.out.println( i +"- Gardien : "+ c.getPlayer().getName() + ", Poste =  "+ c.getPlayer().getPosition() + ", n° =  "+c.getId() +", Type : "+ c.getType() + ", Prix :"+ c.getPrice());
            }
        }
    }

    /**
     * Fonction pour créer une équipe
     */
    public void createTeam(){
        System.out.println("*                                      *");
        System.out.println("***       Création d'une équipe      ***");
        System.out.println("*                                      *");
        
        boolean isWrongAnswer = true;
        do {
            System.out.println("1 - Créer l'équipe pour la semaine "+ (this.sys.getEvent().getWeek()));
            System.out.println("2 - Retour");
            switch (sc.nextLine()) {
                case "1":
                    this.team_next.createTeam();
                    break;
                case "2":
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("Mauvais choix !");
                }
            } while (isWrongAnswer);
    }
    /**
     * Fonction main du menu pour l'utilisateur
     */
    public void main(){
        
        boolean isWrongAnswer = true;
        do {
            System.out.println("****************************************");
            System.out.println("*                                      *");
            System.out.println("***    Bienvenue "+ this.name +"     ***");
            System.out.println("*                                      *");
            System.out.println("* Solde actuel : "+ this.piece +" DOGE *");
            System.out.println("****************************************");

            System.out.println("1 - Evemenent à venir");
            System.out.println("2 - Voir les résultats");
            System.out.println("3 - Liste des cartes");
            System.out.println("4 - Voir mon équipe");
            System.out.println("5 - Créer son équipe");
            System.out.println("6 - Marché");
            System.out.println("7 - Se  déconnecter");
            System.out.println("Faites un choix ?");
            switch (sc.nextLine()) {
                case "1":
                    this.sys.EventComming();
                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "2":
                    if(this.team_now.getCards().size()>0){
                        System.out.println(this.sys.getResults());
                    }
                    else System.out.println("Les résultats ne sont pas encore disponible.");

                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "3":
                    CardList();
                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "4":
                    this.team_next.showTeam();
                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "5":
                    this.createTeam();
                    break;
                case "6":
                    this.sys.getShop().shop(this);
                    break;
                case "7":
                    this.sys.setUsers(this);
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("Mauvais choix !");
                }
            } while (isWrongAnswer);
        }


    
}
