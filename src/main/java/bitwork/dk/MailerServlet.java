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
import java.util.logging.Logger;

/**
 * Created by cha on 30-08-2015.
 */
public class MailerServlet extends HttpServlet{
    Logger logger;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger = Logger.getLogger(MailerServlet.class.getName());
        Map<String, User> users = new FireBaseRetriever().retrieveNotifications();
        Map<String, Tilbud> alleTilbud = new FireBaseRetriever().retrieveAlleTilbud();

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

        IEmailSender sender = new HtmlEmailSender(getServletContext().getResourceAsStream("/email.html"),getServletContext().getResourceAsStream("/images/logo.png"));
        for (TilbudEmail email : allEmails) {
            sender.sendEmailApi(email);
        }
    }




}
