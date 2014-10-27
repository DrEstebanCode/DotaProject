import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrew Seidl on 10/27/2014.
 */

// This class creates and API instance that just reads in your API key
// from the apikey.text
// Made it it's own class due to the amount of use it will need.
public class Api {
    String ApiKey;

    // Constructor that reads in API key from apikey.txt
    public Api(){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("src\\apikey.txt"));
            ApiKey = br.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Get function (good practice to have this correct?)
    public String getApiKey(){
        return ApiKey;
    }

    // Set function (good practice to have this too?) 8==D
    public void setApiKey(String key){
        ApiKey = key;
    }
}
