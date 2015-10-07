package bitwork.dk;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by cha on 20-09-2015.
 */
public class EmailSenderTest extends TestCase {
    @Test
    public void testSendEmailApi() throws Exception {
        InputStream html = getClass().getResourceAsStream("/email.html");
        InputStream logo = getClass().getResourceAsStream("/images/logo.png");
        TilbudEmail tilbudEmail = new TilbudEmail();
        tilbudEmail.receipients = "carverdk@gmail.com;carverdk@yahoo.dk";

        tilbudEmail.tilbud.add(new Tilbud("testButikken","hyldeengen 4, 2791 Dragør","2791","199","25","Pizza","Lækker pizza"));
        tilbudEmail.tilbud.add(new Tilbud("testButikken2","hyldeengen 4, 2791 Dragør","2791","399","25","Pizzabrød","Super Lækker pizza"));

        new HtmlEmailSender(html,logo).sendEmailApi(tilbudEmail);
    }
}