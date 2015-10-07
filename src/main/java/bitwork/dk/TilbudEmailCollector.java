package bitwork.dk;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by cha on 19-09-2015.
 */
public class TilbudEmailCollector {
    int maxReceipients = 10;
    Map<String, TilbudEmail> tilbudEmails = new HashMap<String,TilbudEmail>();

    public void addTilbud(Tilbud tilbud) {
        String postNummer = tilbud.butik.postNummer;
        if(tilbudEmails.get(postNummer) == null){
            tilbudEmails.put(postNummer,new TilbudEmail());
        }
        tilbudEmails.get(postNummer).tilbud.add(tilbud);
    }

    public void addUser(User user) {
        String postNummer = user.getPostcode();
        if(tilbudEmails.get(postNummer) == null){
            tilbudEmails.put(postNummer,new TilbudEmail());
        }
        tilbudEmails.get(postNummer).users.add(user);
        tilbudEmails.get(postNummer).bynavn = user.getBynavn();
    }

    public Set<String> getPostCodes() {
        return tilbudEmails.keySet();
    }

    public List<TilbudEmail> buildEmail(String postCode) {
        List<TilbudEmail> result = new ArrayList<>();
        String content = createContent(postCode);
        List<String> batches = new ArrayList<>();


        StringBuffer receipientLine = new StringBuffer();

        int cnt = 0;
        for (User user : tilbudEmails.get(postCode).users) {
            renderUser(receipientLine, user);
            if(cnt > maxReceipients){
                batches.add(receipientLine.toString());
                receipientLine = new StringBuffer();
            }
        }
        batches.add(receipientLine.toString());

        for (String batch : batches) {
            TilbudEmail email = new TilbudEmail();
            email.contents = content;
            email.receipients = batch;
            result.add(email);
        }
        return result;
    }

    private String createContent(String postCode) {
        StringBuffer content = new StringBuffer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("w - yyyy");
        String format = simpleDateFormat.format(new Date());
        TilbudEmail tilbudEmail = tilbudEmails.get(postCode);

        content.append("<h1> Ugens tilbud fra MinLokaleButik.dk</h1>");
        content.append("<h2>"+tilbudEmail.bynavn.substring(0, 1).toUpperCase() + tilbudEmail.bynavn.substring(1)+" Uge "+format+"</h2>");

        for (Tilbud tilbud : tilbudEmail.tilbud) {
            renderTilbud(content, tilbud);
        }
        content.append("<h2>Kom ind på <a href=\"http://minlokalebutik.dk\">minlokalebutik.dk</a> og se flere tilbud.</h2>");
        return content.toString();
    }


    private void renderUser(StringBuffer receipient, User user) {
        receipient.append(user.getEmail()+";");
    }

    private void renderTilbud(StringBuffer buffer, Tilbud tilbud) {
        buffer.append("<p><h3>"+tilbud.kort+ " " + tilbud.pris + " kr " + "spar ("+ tilbud.rabat+" %)"+"</h3>");
        buffer.append("<p>"+tilbud.lang+"</p>");
        buffer.append("<p>"+tilbud.butik.navn+","+tilbud.butik.adresse+"</p>");
    }

}
