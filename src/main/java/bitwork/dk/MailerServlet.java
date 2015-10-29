package bitwork.dk;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

        List<TilbudEmail> allEmails = createEmails(users, alleTilbud);

        IEmailSender sender = new HtmlEmailSender("/email.html");
        for (TilbudEmail email : allEmails) {
            if(email.tilbud.size() > 0){
                sender.sendEmailApi(email);
            }
        }
    }

    protected List<TilbudEmail> createEmails(Collection<User> users, Collection<Tilbud> alleTilbud) {
        TilbudEmailCollector collector = new TilbudEmailCollector();

        for (Tilbud tilbud : alleTilbud){
            if(tilbud.active()){
                collector.addTilbud(tilbud);
            }
        }

        for (User user : users){
            collector.addUser(user);
        }

        List<TilbudEmail> allEmails = new ArrayList<>();
        for (String postCode : collector.getPostCodes()) {
            List<TilbudEmail> emails = collector.buildEmail(postCode);
            allEmails.addAll(emails);
        }
        return allEmails;
    }




}
