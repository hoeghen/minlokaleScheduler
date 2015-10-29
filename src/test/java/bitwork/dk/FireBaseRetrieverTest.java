package bitwork.dk;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Kan jeg hente tilbud og emails fra firebase
 * Created by cha on 04-10-2015.
 */
public class FireBaseRetrieverTest extends TestCase {

    public void testRetrieveNotifications() throws Exception {
        Map<String, User> notifications = new FireBaseRetriever().retrieveNotifications();
        Assert.assertTrue(notifications.keySet().size() > 0);
    }

    public void testRetrieveAlleTilbud() throws Exception {
        Map<String, Tilbud> stringTilbudMap = new FireBaseRetriever().retrieveAlleTilbud();
        Assert.assertTrue(stringTilbudMap.keySet().size() > 0);


        Collection<Tilbud> values = stringTilbudMap.values();

        for (Tilbud value : values) {
            System.out.println("acvtive="+value.active() + "   slut="+value.getSlut());
        }


    }
}