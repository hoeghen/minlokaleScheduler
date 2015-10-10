package bitwork.dk;

/**
 * Created by cha on 19-09-2015.
 */
public class Butik {
    String adresse;
    String navn;

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    String postnummer;

    public Butik(String adresse, String navn, String postnummer) {
        this.adresse = adresse;
        this.navn = navn;
        this.postnummer = postnummer;
    }

    public Butik() {
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }


}
