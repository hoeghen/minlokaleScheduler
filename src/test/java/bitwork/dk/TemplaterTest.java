package bitwork.dk;

import junit.framework.TestCase;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by cha on 06-10-2015.
 */
public class TemplaterTest extends TestCase {

    public void testSingleTemplater(){
        String html = "sdf sfsdfsd ${email.bynavn}";
        TilbudEmail email = new TilbudEmail();
        email.bynavn = "Dragør";
        String apply = Templater.apply(email, html);
        assertEquals(apply,"sdf sfsdfsd Dragør");

    }

    public void testLoopTemplater(){
        String html = "Start " +
                "${foreach email.tilbud tilbud}" +
                "${tilbud.kort} og  ${tilbud.pris} nemlig"+
                "${end}" +
                "Slut";



        TilbudEmail email = new TilbudEmail();
        email.bynavn = "Dragør";
        Tilbud tilbud = new Tilbud();
        tilbud.pris = "100";
        tilbud.kort = "kort";

        Tilbud tilbud2 = new Tilbud();
        tilbud2.pris = "200";
        tilbud2.kort = "2kort";

        email.tilbud.add(tilbud);
        email.tilbud.add(tilbud2);

        String apply = Templater.apply(email, html);
        assertEquals(apply,"Start kort og  100 nemlig2kort og  200 nemligSlut");

    }


}
