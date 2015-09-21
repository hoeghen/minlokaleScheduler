package bitwork.dk;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cha on 01-09-2015.
 */
public class MailerServletTest extends TestCase {

    public void testDoGet() throws Exception {

    }

    public void testCreateAndSendEmail() throws Exception {
        System.out.println("start");
        List<Tilbud> tilbuds = new ArrayList<>();
        List<User> users = new ArrayList<>();

        tilbuds.add(new Tilbud(new Butik("Hyldeengen 4","Dragør","2100"),"200","13","kort","lang"));
        tilbuds.add(new Tilbud(new Butik("Hyldeengen 3","Dragør","2100"),"200","13","kort","lang"));
        tilbuds.add(new Tilbud(new Butik("Hyldeengen 2","Dragør","2100"),"200","13","kort","lang"));

        users.add(new User("ddd@dd.dk","2100","dragør"));
        users.add(new User("ddd2@dd.dk","2100","dragør"));
        users.add(new User("ddd3@dd.dk","2100","dragør"));
        users.add(new User("ddd4@dd.dk", "2100", "dragør"));

        new MailerServlet().createAndSendEmail(users,tilbuds);


    }
}