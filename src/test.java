import java.io.IOException;

import model.system.System_M;
import model.system.Database;
public class test {
    public static void main(String[] args) throws IOException {
        // Système
        System_M sys = new System_M();
        // Data base
        Database db = new Database();
        sys.setDatabase(db);
        sys.StartGame();
    }
}
