/**
 * Yossi Silberhaft 210028924 89-281-04
 * Daniel Blinick WN654676 89-281-04
 * Binyamin Greenberg 200220671 89-281-04
 * Steven Lapp 204785240 89-281-05
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This Class will read the configuration file given to us
 * and store the details for use by the main program.
 */
public class ReadConfig {

    public DBConnectionInfo getConnectionInfoFromFile(File fileName) {
        //Deceleration of Variables
        String dbURL = "";
        String userName = "";
        String password = "";
        int counter = 1;

        //Sets the buffer reader to NULL
        BufferedReader br = null;

        //Gets the DB information from the Config.txt file
        try{
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null){
                if(counter == 1){
                    dbURL = sCurrentLine;
                    counter++;
                }else if(counter == 2){
                    userName = sCurrentLine;
                    counter++;
                }else if(counter == 3){
                    password = sCurrentLine;
                    counter++;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            try {
                if (br != null) br.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return new DBConnectionInfo(userName, password, dbURL);
    }
}