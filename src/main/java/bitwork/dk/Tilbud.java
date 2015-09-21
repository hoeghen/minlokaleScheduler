package bitwork.dk;

/**
 * Created by cha on 30-08-2015.
 */
public class Tilbud {
    Butik butik;
    String pris;
    String rabat;
    String kort;
    String lang;

    public Tilbud(Butik butik, String pris, String rabat, String kort, String lang) {
        this.butik = butik;
        this.pris = pris;
        this.rabat = rabat;
        this.kort = kort;
        this.lang = lang;
    }
}
