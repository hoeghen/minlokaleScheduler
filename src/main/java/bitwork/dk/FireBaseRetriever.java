package bitwork.dk;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by cha on 04-10-2015.
 */
public class FireBaseRetriever {
        String charSet = "UTF-8";
        String firebaseRef = "jobspot.firebaseio.com";
        Logger logger = Logger.getLogger(FireBaseRetriever.class.getName());

        public Map<String, User> retrieveNotifications() throws IOException {
            URL url = new URL("https://"+firebaseRef+"/notifications.json");
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream(),charSet);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            Type type = new TypeToken<Map<String, User>>(){}.getType();

            Map<String,User> notifications = new GsonBuilder().create().fromJson(reader, type);
            logger.info("retrieved ["+notifications.size()+"] subscribers from firebase");
            return notifications;
        }

        public Map<String, Tilbud> retrieveAlleTilbud() throws IOException {
            BufferedReader reader;
            URL url = new URL("https://"+firebaseRef+"/alletilbud.json");
            reader = new BufferedReader(new InputStreamReader(url.openStream(),charSet));
            Type type = new TypeToken<Map<String, Tilbud>>(){}.getType();
            Map<String,Tilbud> alleTilbud = new GsonBuilder().create().fromJson(reader, type);

            if (logger.isLoggable(Level.FINE)){
                logger.fine("Tilbud url = " + url.toString());
                InputStreamReader inputStreamReader2 = new InputStreamReader(url.openStream());
                String inputStreamString = new Scanner(inputStreamReader2).useDelimiter("\\Z").next();
                logger.fine("result = " + inputStreamString);
            }
            logger.info("retrieved ["+alleTilbud.size()+"] tilbud from firebase");
            return alleTilbud;
        }

}
