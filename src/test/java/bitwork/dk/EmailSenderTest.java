package bitwork.dk;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cha on 20-09-2015.
 */
public class EmailSenderTest extends TestCase {
    @Test
    public void testSendEmailApi() throws Exception {
        InputStream resourceAsStream = getClass().getResourceAsStream("/email.html");
        String html = StreamHelper.getStringFromInputStream(resourceAsStream);

        TilbudEmail tilbudEmail = new TilbudEmail();
        List<Modtager> builder = Modtager.builder(new User("carverdk@gmail", "2791", "Dragør"), new User("carverdk@gmail", "2791", "Dragør"));
        tilbudEmail.receipients = builder.get(0);
        tilbudEmail.setUgenummer("11");
        tilbudEmail.setBynavn("Korsør");
        tilbudEmail.tilbud.add(new Tilbud("testButikken", "hyldeengen 4, 2791 Dragør", "2791", "199", "25", "Pizza", "Lækker pizza"));
        tilbudEmail.tilbud.add(new Tilbud("testButikken2","hyldeengen 4, 2791 Dragør","2791","399","25","Pizzabrød","Super Lækker pizza"));

        new HtmlEmailSender(html).sendEmailApi(tilbudEmail);
    }
}