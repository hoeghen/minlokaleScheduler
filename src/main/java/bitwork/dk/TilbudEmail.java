package bitwork.dk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cha on 19-09-2015.
 */
public class TilbudEmail {
    String postCode;
    String bynavn;
    private String ugenummer;
    List<Tilbud> tilbud = new ArrayList<Tilbud>();
    List<User> users = new ArrayList<User>();
    Modtager receipients;

    @Override
    public String toString() {
        return "TilbudEmail{" +
                "postCode='" + postCode + '\'' +
                ", bynavn='" + bynavn + '\'' +
                ", ugenummer='" + ugenummer + '\'' +
                ", tilbud=" + tilbud +
                ", users=" + users +
                ", receipients='" + receipients + '\'' +
                '}';
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }

    public List<Tilbud> getTilbud() {
        return tilbud;
    }

    public void setTilbud(List<Tilbud> tilbud) {
        this.tilbud = tilbud;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUgenummer() {
        return ugenummer;
    }

    public void setUgenummer(String ugenummer) {
        this.ugenummer = ugenummer;
    }
}
