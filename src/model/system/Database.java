package model.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static Database d = null;
    
    public Database(){};

    public static Database getInstance(){
        if(Database.d == null){
            Database.d = new Database();
        }
        return Database.d;
    }

    /**
     * Fonction pour verifer si l'utilisateur existe
     * @param name Son nom d'utilisateur
     * @param pass Son mot de passe
     * @return true or false
     */
    public boolean IsExistUser( String name, String pass){
        
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\db\\User.csv"))) {
            String line;
            while ((line = br.readLine()) != null) { // Recupération des données existante
                String[] values = line.split(";");
                if(values[0].equals(name) && values[1].equals(pass)) return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Fonction pour récupérer les informations de l'utilisateur
     * @param name Son nom d'utilisateur
     * @param pass Son mot de passe
     * @return List des données
     */
    public List<String> getUserbyInfo( String name, String pass){
        
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\db\\User.csv"))) {
            String line;
            while ((line = br.readLine()) != null) { // Recupération des données existante
                String[] values = line.split(";");
                if(values[0].equals(name) && values[1].equals(pass)) return Arrays.asList(values);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fonction permettant à un utilisateur de s'inscrire
     * @param name Son nom
     * @param pass Son mot de passe
     */
    public void register(String name, String pass){
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Données\\db\\User.csv", true))) {
            bw.write(name+";"+pass+";10000"+"\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    /**
     * Fonction permettant de récuperer la liste des semaine
     */
    public String[] get_Event_Weeks(){
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\db\\Weeks.csv"))) {
            String line;
            while ((line = br.readLine()) != null) { // Recupération des données existante
                String[] values = line.split(";");
                return values;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fonction permettant de mettre à jour les semaines
     */
    public void set_Event_Weeks(int prev, int next){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Données\\db\\Weeks.csv"))) {
                bw.write(prev+";"+next+"\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Fonction reinitialisant les confirgurations
     */
    public void init_Event_Weeks(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Données\\db\\Weeks.csv"))) {
                bw.write("42;46\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   

    // Obtenir les resultats des compétences des différents match effectué
    public List<List<String>> getResult(int semaine, String team){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\2021_" + semaine + "\\2021_" + semaine + "_" + team + ".csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("	");
                records.add(Arrays.asList(values));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return records;
    }

    // Obtenir les resultats des compétences des différents match effectué
    public List<List<String>> getMarketCard(){

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\db\\Market.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return records;
    }

    // Obtenir les matchs a venir
    public List<List<String>> getMatch(int semaine){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Données\\2021_" + semaine + "\\2021_" + semaine + "_matchs.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" / ");
                records.add(Arrays.asList(values));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return records;
    }
}
