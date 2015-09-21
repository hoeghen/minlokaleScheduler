package bitwork.dk;

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
        content.append("<h1> Ugens tilbud fra MinLokaleButik.dk</h1>");
        TilbudEmail tilbudEmail = tilbudEmails.get(postCode);
        for (Tilbud tilbud : tilbudEmail.tilbud) {
            renderTilbud(content, tilbud);
        }
        return content.toString();
    }


    private void renderUser(StringBuffer receipient, User user) {
        receipient.append(user.getEmail()+";");
    }

    private void renderTilbud(StringBuffer buffer, Tilbud tilbud) {
        buffer.append("<p><h2>"+tilbud.kort+"</h2>");
        buffer.append(tilbud.pris + " kr" + " rabat "+tilbud.rabat + "% </p>");
    }

}
