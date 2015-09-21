package bitwork.dk;

import com.google.appengine.repackaged.com.google.common.collect.Multimap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by cha on 30-08-2015.
 */
public class MailerServlet extends HttpServlet{
    String firebaseRef = "jobspot.firebaseio.com";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, User> users = retrieveNotifications();
        Map<String, Tilbud> alleTilbud = retrieveAlleTilbud();

        createAndSendEmail(users.values(), alleTilbud.values());
    }

    protected void createAndSendEmail(Collection<User> users, Collection<Tilbud> alleTilbud) {
        TilbudEmailCollector collector = new TilbudEmailCollector();

        for (Tilbud tilbud : alleTilbud){
            collector.addTilbud(tilbud);
        }

        for (User user : users){
            collector.addUser(user);
        }

        List<TilbudEmail> allEmails = new ArrayList<>();
        for (String postCode : collector.getPostCodes()) {
            List<TilbudEmail> emails = collector.buildEmail(postCode);
            allEmails.addAll(emails);
        }

        for (TilbudEmail email : allEmails) {
            EmailSender.sendEmail(email);
        }
    }


    private Map<String, User> retrieveNotifications() throws IOException {
        URL url = new URL("https://"+firebaseRef+"/notifications.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Type type = new TypeToken<Map<String, User>>(){}.getType();
        Map<String,User> notifications = new GsonBuilder().create().fromJson(reader, type);
        return notifications;
    }

    private Map<String, Tilbud> retrieveAlleTilbud() throws IOException {
        BufferedReader reader;
        URL url = new URL("https://"+firebaseRef+"/alletilbud.json");
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Type type = new TypeToken<Map<String, Tilbud>>(){}.getType();
        Map<String,Tilbud> alleTilbud = new GsonBuilder().create().fromJson(reader, type);
        return alleTilbud;
    }


}
