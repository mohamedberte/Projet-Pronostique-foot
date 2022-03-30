package model.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import model.cards.Card_M;
import model.team.Team_M;
import model.user.Team_user_M;
import model.user.User_M;
import model.visitor.ScoreCalculator;
import model.visitor.Visitor;

public class System_M {
    
    // Programme les evenements à venir et affiche les résulats
    private Event_M event;
    private Database database;
    private Market shop;
    private List<User_M> users = new ArrayList<>();
    private String results;

    // scanner
    private static Scanner sc = new Scanner(System.in);
    // Constructeur 
    public System_M(){
        this.event = null;
        this.database = null;
        this.shop = Market.getInstance();
        this.results = new String();
    }
    public System_M(Event_M event, Database database){
        this.event = event;
        this.database = database;
        this.shop = Market.getInstance();
        this.results = new String();
    }

    // Getter et Setter
    protected void setEvent( Event_M event){
        this.event = event;
    }
    public void setDatabase( Database database){
        this.database = database;
    }
    public String getResults() {
        return results;
    }
    public Market getShop() {
        return shop;
    }
    public Database getDatabase(){
        return this.database;
    }
    public Event_M getEvent(){
        return this.event;
    }
    public void setUsers(User_M user) {
        this.users.add(user);
    }
    // Recupérer les matchs à venir
    public void EventComming(int semaine){
        List<List<String>> teams_vs = this.database.getMatch(semaine);
        System.out.println("*******************************************");
        System.out.println("Liste des Matchs à venir semaine ("+ semaine +")");
        System.out.println("-------------------------------------------");

        if( teams_vs.size() > 0){
            System.out.println("-     Il y aura "+ teams_vs.size() +" Matchs     -");
            for( List<String> line : teams_vs ){
                System.out.println(line.get(0) + " vs " + line.get(1));
            }
        }
        else{
            System.out.print("- Il y aura pas de Match -");
        }
        System.out.println("*******************************************");
        System.out.println("\n");
    }
    public void EventComming(){
        List<List<String>> teams_vs = this.database.getMatch(this.event.getWeek());
        System.out.println("*******************************************");
        System.out.println("Liste des Matchs à venir semaine ("+ this.event.getWeek() +")");
        System.out.println("-------------------------------------------");
        if( teams_vs.size() > 0){
            System.out.println("- Il y aura "+ teams_vs.size() +" Matchs -");
            for( List<String> line : teams_vs ){
                System.out.println(line.get(0) + " vs " + line.get(1));
            }
        }
        else{
            System.out.println("- Il y aura pas de Match -");
        }
        System.out.println("*******************************************");
        System.out.println("\n");
    }

    public void nextEvent(){
        // Loading of events data len 2 : week now / next week
        String[] weeks = this.database.get_Event_Weeks();
        this.EventComming(Integer.parseInt(weeks[1]));
    }

    // Obtenir le resumé des joueurs
    public void Summary(int semaine, String team){
        List<List<String>> summary = this.database.getResult(semaine, team);
        System.out.println("*******************************************");
        System.out.println("Compétence des joueurs de "+ team +" en fin de match SEM ("+ semaine +")");
        System.out.println("-------------------------------------------");
        int i = 1;
        for( List<String> line : summary ){
            if(line.get(11).equals("-")) System.out.println("" + i + "- " + line.get(1) +", Poste : " + line.get(10) + ", Note : 0 ");
            else System.out.println("" + i + "- " + line.get(1) +", Poste : " + line.get(10) + ", Note : " + line.get(11));
            i = i + 1;
        }
    }

    // Obtenir la liste des joueurs d'une équipe pour une semaine donnée
    public List<List<String>> getPlayerTeam(String team){
        List<List<String>> summary = this.database.getResult(this.event.getWeek(), team);
        List<List<String>> players = new ArrayList<>();
        for( List<String> line : summary ){
            List<String> player = new ArrayList<>();
            player.add(line.get(1));
            player.add(line.get(10));
            if(line.get(11).equals("-")) player.add("0");
            else player.add(line.get(11));
            
            players.add(player);
        }
        return players;
    }

    public List<List<String>> getPlayerTeam(int semaine, String team){
        List<List<String>> summary = this.database.getResult(semaine, team);
        List<List<String>> players = new ArrayList<>();
        for( List<String> line : summary ){
            List<String> player = new ArrayList<>();
            player.add(line.get(0));
            player.add(line.get(10));
            
            players.add(player);
        }
        return players;
    }

    private void main(){
        // Déclencher la remise des récompenses hebdomadaires.
        System.out.println("*                                   *");
        System.out.println("**** Vous êtes en tant que ADMIN ****");
        System.out.println("*                                   *");

        boolean isWrongAnswer = true;
        do {
            System.out.println("1 - Récompenser");
            System.out.println("2 - Se déconnecter");

            switch (sc.nextLine()) {
                case "1":
                    this.awardEvent();
                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "2":
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("Mauvais choix !");
                }
            } while (isWrongAnswer);
        }


    public void awardEvent(){

        Map<Double,User_M> winner = new HashMap<>();

        for(User_M user: this.users){
            if(user.getTeam_next().getCards().size()==4){
                Visitor v = new ScoreCalculator();
                Double r = user.getTeam_next().accept(v);
                winner.put(r, user);
                // Equipe ancienne puis reinitialisation
                user.setTeam_now(user.getTeam_next());
                user.setTeam_next(new Team_user_M(user));
            }
        }

        // S'il y'a au moins un gagnant
        if(winner.size()>0){
            Map<Double, User_M> winSort = new TreeMap<>(winner);
            //Parcourir le Hashmap avec la boucle For
            StringBuilder rs = new StringBuilder();
            rs.append("*****************************************************************************");
            rs.append("\n");
            rs.append("Classement des joueurs ayant participé pour la semaine " + this.event.getWeek());
            rs.append("\n");
            rs.append("*****************************************************************************");
            rs.append("\n");
    
            int range = 1;
            for (Map.Entry<Double, User_M> win : winSort.entrySet()) {
                System.out.println("ID: "+win.getKey()+", Nom: "+win.getValue().getName());
                if(range == 1){
                    Card_M c = this.shop.getRandomCard("RARE");
                    win.getValue().setCard(c);
                    win.getValue().setPiece(win.getValue().getPiece() + 1000);
                    rs.append(range + " - Nom: "+ win.getValue().getName() +", Note : "+ win.getKey());
                    rs.append("\n");
                    rs.append("Somme obtenu : 1000 DOGE");
                    rs.append("\n");
                    rs.append("Carte Obenu :");
                    rs.append("\n");
                    rs.append(c.toString());
                }
                if(range == 2){
                    Card_M c = this.shop.getRandomCard("PEU RARE");
                    win.getValue().setCard(c);
                    win.getValue().setPiece(win.getValue().getPiece() + 500);
                    rs.append(range + " - Nom: "+ win.getValue().getName() +", Note : "+ win.getKey());
                    rs.append("\n");
                    rs.append("Somme obtenu : 500 DOGE");
                    rs.append("\n");
                    rs.append("Carte Obenu :");
                    rs.append("\n");
                    rs.append(c.toString());
                    rs.append("\n");
                }
                if(range == 3){
                    Card_M c = this.shop.getRandomCard("COMMUNE");
                    win.getValue().setCard(c);
                    win.getValue().setPiece(win.getValue().getPiece() + 200);
                    rs.append(range + " - Nom: "+ win.getValue().getName() +", Note : "+ win.getKey());
                    rs.append("\n");
                    rs.append("Somme obtenu : 1000 DOGE");
                    rs.append("\n");
                    rs.append("Carte Obenu :");
                    rs.append("\n");
                    rs.append(c.toString());
                    rs.append("\n");
                }
                if(range>3){
                    rs.append(range + " - Nom: "+ win.getValue().getName() +", Note : "+ win.getKey());
                    rs.append("\n");
                    rs.append("Somme obtenu : 0 DOGE");
                    rs.append("\n");
                }
                range++;
            }
            
            this.results = rs.toString();
            System.out.println("Les résultats ont été publié, il y'a "+winSort.size()+" participant(s).");
        }
        else System.out.println("Pas de participant pour cette semaine") ;
 

        // Semaine suivante
        String[] weeks = this.database.get_Event_Weeks();
        this.init(Integer.parseInt(weeks[1]));

        this.database.set_Event_Weeks(Integer.parseInt(weeks[1]), Integer.parseInt(weeks[1]) + 1);

    }

    // Connexion
    public void connexion(){
        System.out.println("*                                   *");
        System.out.println("************* Connexion *************");
        System.out.println("*                                   *");

        try {

            int tries = 3;
        
            while( tries != 0 ) {
                System.out.println( "Pseudonyme : " );
                String login = sc.nextLine();
                
                System.out.print( "Mot de passe: " );
                String password = sc.nextLine();
                if(login.equals("admin") && password.equals("admin")){
                    this.main();
                    tries = 0;
                    return;
                }
                else{
                    if(this.database.IsExistUser(login, password)){
                        System.out.println("*              Vous êtes connecté           *");
                        // Recupération des données existantes
                        for(User_M u : this.users){
                            if(u.getName().equals(login) && u.getPassword().equals(password)){
                                this.users.remove(u);
                                u.main();
                                return;
                            }
                        }
                        
                        List<String> infos = this.database.getUserbyInfo(login, password);
                        User_M user = new User_M(infos.get(0), infos.get(1), Integer.parseInt(infos.get(2)));
                        user.setSys(this); user.main();
                        return;
                    }
                    else{
                        tries--;
                        System.out.println("*            Information inexistant !        *");
                        System.out.println("*            Il vous restes " + tries + " essaies     *");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("*         Connexion echouée !    *");
        return;
    }


    // Connexion
    public void Register(){
        System.out.println("*                                   *");
        System.out.println("************* Inscription *************");
        System.out.println("*                                   *");

        try {
                System.out.println( "Pseudonyme : " );
                String login = sc.nextLine();
                
                System.out.print( "Mot de passe: " );
                String password = sc.nextLine();
                if(this.database.IsExistUser(login, password)){
                    System.out.println("*              Vous avez déjà un compte           *");
                }
                else{
                    this.database.register(login, password);
                    System.out.println("*            Vous êtes inscrit !      *");
                    return;
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    // Initialisation
    public void init(int week){

        // week 
        this.event = new Event_M(week);

        // loading game_play
        List<List<String>> teams_vs = this.database.getMatch(week);

        List<Game_M> games = new ArrayList<>();
        for(List<String> vs_line : teams_vs){
            // Création d'équipe pour Nantes
            if(vs_line.get(0).equals("FC Nantes") || vs_line.get(1).equals("FC Nantes")){
                Game_M game = new Game_M();
                Team_M team = new Team_M();
                team.setName("FC Nantes");

                List<List<String>> players = getPlayerTeam("Nantes");

                team.createTeamByList(players);

                // Synch avec le marché
                this.shop.setPlayers(team.getPlayers());
                this.shop.setPlayers(team.getPlayers());
                game.setTeam1(team);
                if(vs_line.get(0).equals("FC Nantes")){ game.setTeam1(team); game.setTeam2(new Team_M(vs_line.get(1)));}
                else{game.setTeam1(team); game.setTeam2(new Team_M(vs_line.get(1)));}
                
                // Ajout dans la liste des matchs
                games.add(game);
            }
            // Création d'équipe pour France
            if(vs_line.get(0).equals("France") || vs_line.get(1).equals("France")){
                Game_M game = new Game_M();
                Team_M team = new Team_M();
                team.setName("France");

                List<List<String>> players = getPlayerTeam("France");
                team.createTeamByList(players);

                // Synch avec le marché
                this.shop.setPlayers(team.getPlayers());
                game.setTeam1(team);
                if(vs_line.get(0).equals("France")){ game.setTeam1(team); game.setTeam2(new Team_M(vs_line.get(1)));}
                else{game.setTeam1(team); game.setTeam2(new Team_M(vs_line.get(1)));}
            
                // Ajout dans la liste des matchs
                games.add(game);
            }

        }
        this.event.setMatchs(games);
        this.shop.initMarket();
    }

    public void StartGame(){
        
        boolean isWrongAnswer = true;
        init(42);
        do {
            System.out.println("****************************************");
            System.out.println("** Bienvenue sur le JEUX FOOT-POLYBET **");
            System.out.println("*              Semaine "+(this.event.getWeek() - 1)+"              *");
            System.out.println("****************************************");
            System.out.println("1 - Connexion");
            System.out.println("2 - Inscription");
            System.out.println("3 - Information du système");
            System.out.println("4 - Quittez");
            System.out.println("****************************************");


            System.out.println("Faites un choix ?");
            switch (sc.nextLine()) {
                case "1":
                    this.connexion();
                    break;
                case "2":
                    this.Register();
                    break;
                case "3":
                    this.SystemInfo();
                    System.out.println("appuyer n'importe quelle touche + ENTER");
                    sc.nextLine();
                    break;
                case "4":
                    isWrongAnswer = false;
                    break;
                default:
                System.out.println("Mauvais choix");
                }
                
            } while (isWrongAnswer);

        this.database.init_Event_Weeks();
        
    }

    public void SystemInfo(){
        System.out.println("Créer par Mohamed Berté & Fatma Hamdal");
        System.out.println("Appuyez sur une touche pour retourner");
            switch (sc.nextInt()) {
                default:
                break;
            }
    }
}
