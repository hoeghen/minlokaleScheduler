package bitwork.dk;

import java.util.Date;

/**
 * Created by cha on 30-08-2015.
 */
public class Tilbud {
    Butik butik;
    String pris;
    String rabat;
    String kort;
    String lang;

    Date slut;
    Date start;

    public Tilbud(Butik butik, String pris, String rabat, String kort, String lang) {
        this.butik = butik;
        this.pris = pris;
        this.rabat = rabat;
        this.kort = kort;
        this.lang = lang;
    }

    public Tilbud(String butikNavn, String butikAdresse,String postnummer,String pris, String rabat, String kort, String lang) {
        this.butik = new Butik(butikAdresse,butikNavn,postnummer);
        this.pris = pris;
        this.rabat = rabat;
        this.kort = kort;
        this.lang = lang;
    }

    public Tilbud() {
    }

    public Butik getButik() {
        return butik;
    }

    public void setButik(Butik butik) {
        this.butik = butik;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getRabat() {
        return rabat;
    }

    public void setRabat(String rabat) {
        this.rabat = rabat;
    }

    public String getKort() {
        return kort;
    }

    public void setKort(String kort) {
        this.kort = kort;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    public Date getSlut() {
        return slut;
    }

    public void setSlut(Date slut) {
        this.slut = slut;
    }
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public boolean active() {
        if(slut == null || !slut.before(new Date())){
            return true;
        }else{
            return false;
        }

    }
}
