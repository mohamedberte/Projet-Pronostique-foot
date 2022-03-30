package model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import model.cards.Card_M;
import model.cards.Common_card_M;
import model.cards.Middle_card_M;
import model.cards.Rare_card_M;
import model.cards.decorateur.Limited;
import model.cards.decorateur.Special_card;
import model.team.Player_M;
import model.user.User_M;

public class Market {
    private static Market market = null;

    // List of cards
    List<Card_M> cards = new ArrayList<>();
    List<Player_M> players = new ArrayList<>();


    // scanner
    private static Scanner sc = new Scanner(System.in);

    private Market(){};

    public static Market getInstance(){
        if(Market.market == null){
            Market.market = new Market();
        }
        return Market.market;
    }

    protected void setPlayers(List<Player_M> players){
        for( Player_M p : players){
            if(! this.IsExistPlayerByName(p.getName()))this.players.add(p);
        }
    }
    protected void setCards(List<Card_M> cards) {
        this.cards = cards;
    }

    protected void addCard(Card_M cards) {
        this.cards.add(cards);
    }

    protected List<Card_M> getCards() {
        return cards;
    }
    protected List<Player_M> getPlayers() {
        return players;
    }

    private void CardList(){
        // Afficher les informations des carte
        System.out.println("Liste des cartes du marché");
        System.out.println("Il y'a "+ this.cards.size() + " carte(s) en vente");
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

    private boolean IsExistPlayerByName(String name){
        for( Player_M p : this.players){
            if(p != null){
                if(p.getName().equals(name)) return true;
            }
        }
        return false;
    }

    private int gen(int max) {
		int range = (max - 1) + 1;
		return (int)(Math.random() * range) + 1;
	}

    protected Card_M getRandomCard(String type) {
        List<Card_M> rc = new ArrayList<>();

        for(Card_M c : this.cards){
            if(c.getType().equals(type)){
                rc.add(c);
            }
        }
        if(rc.size() > 0){
            int rd_indice = gen(rc.size() - 1);
            Card_M rd_card = rc.get(rd_indice);
            this.cards.remove(rd_card);
            return rd_card;
        }
        return null;
	}

    public void initMarket(){
        
        for(Player_M p : this.players ){
            
            // Génération COMMON CARD
            for(int i = 1;i<=this.gen(2); i++){
                Card_M c = new Common_card_M(p);
                if(c != null) this.cards.add(c);
            }

            // Génération Middle CARD
            for(int i = 1;i<=this.gen(2); i++){
                Card_M c = new Middle_card_M(p);
                if(c != null) this.cards.add(c);
            }

            // Génération Rare CARD
            for(int i = 1;i<=this.gen(1); i++){
                Card_M c = new Rare_card_M(p);
                if(c != null) this.cards.add(c);
            }
            // Génération Limited CARD Rare
            for(int i = 1;i<=this.gen(1); i++){
                Special_card c = new Limited(new Rare_card_M(p));
                if(c != null) this.cards.add(c);
            }
        }
    }

    protected void buy(User_M user){

        boolean isWrongAnswer = true;
        do {

            this.CardList();
            System.out.println("Entrer le numéro de la carte correspondant :");

            int num = Integer.parseInt(sc.nextLine());
            if( num < 1 || num > this.cards.size()) {
                System.out.println("Vous avez fait le mauvais choix");
            }
            else{

                int price = this.cards.get(num-1).getPrice();
                if(user.getPiece() - price > 0){
                    user.setCard(this.cards.get(num - 1));
                    user.setPiece(user.getPiece() - price);
                    this.cards.remove(num - 1);

                    System.out.println("Achat effectué avec succès !");
                    System.out.println("Il vous reste "+ user.getPiece()+ " DOGE");
                }
                else System.out.println("Votre solde est insuffisant");
            }

            System.out.println("Voulez vous effectuer un nouveau achat ? (y/n)");
            
            String choix = sc.nextLine();
            switch (choix) {
                case "y":
                    break;
                case "n":
                    isWrongAnswer = false;
                    break;
                default:
                    System.out.println("Mauvais choix");
            }
        } while (isWrongAnswer);
    }

    protected void sell(User_M user){

        boolean isWrongAnswer = true;
        do {
            user.CardList();
            if(user.getCard().size() > 0){
                System.out.println("Entrer le numéro de la carte correspondant :");

                int num = Integer.parseInt(sc.nextLine());
                if( num < 1 || num > user.getCard().size()) {
                    System.out.println("Vous avez fait le mauvais choix");
                }
                else{

                    if( !user.getCard().get(num - 1).IsOrdered()){
                        user.setPiece(user.getPiece() + user.getCard().get(num - 1).getPrice());
                        this.cards.add(user.getCard().get(num - 1));
                        user.getCard().remove(num - 1);

                        System.out.println("Vente effectué avec succès !");
                        System.out.println("Il vous reste "+ user.getPiece()+ " DOGE");
                    }
                    else System.out.println("Oups ! Cette carte fait partie d'une équipe que vous avez constitué") ;         
                }

                System.out.println("Voulez vous effectuer une nouvelle vente ? (y/n)");
                
                String choix = sc.nextLine();
                switch (choix) {
                    case "y":
                        break;
                    case "n":
                        isWrongAnswer = false;
                        break;
                    default:
                        System.out.println("Mauvais choix");
                }
            }
            else isWrongAnswer = false;
            
        } while (isWrongAnswer);
    }

    public void shop(User_M user){
        boolean isWrongAnswer = true;
        do {
            System.out.println("*                                    *");
            System.out.println("***    Bienvenue sur le marché     ***");
            System.out.println("*                                    *");
            System.out.println("1 - Acheter");
            System.out.println("2 - Vendre");
            System.out.println("3 - Retour");


            System.out.println("Faites un choix ?");
            switch (sc.nextLine()) {
                case "1":
                    this.buy(user);
                    break;
                case "2":
                    this.sell(user);;
                    break;
                case "3":
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("Mauvais choix");
                }
                
            } while (isWrongAnswer);
    }
}
