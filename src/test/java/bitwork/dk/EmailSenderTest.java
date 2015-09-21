package bitwork.dk;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by cha on 20-09-2015.
 */
public class EmailSenderTest extends TestCase {
    @Test
    public void testSendEmailApi() throws Exception {
        EmailSender.sendEmailApi(new TilbudEmail());
    }
}