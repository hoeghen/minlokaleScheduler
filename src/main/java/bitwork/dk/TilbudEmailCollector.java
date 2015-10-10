package bitwork.dk;

import java.util.*;

/**
 * Created by cha on 19-09-2015.
 */
public class TilbudEmailCollector {
    int maxReceipients = 10;
    Map<String, TilbudEmail> postCodeMap = new HashMap<String,TilbudEmail>();

    public void addTilbud(Tilbud tilbud) {
        String postNummer = tilbud.butik.postnummer;
        if(postCodeMap.get(postNummer) == null){
            postCodeMap.put(postNummer, new TilbudEmail());
        }
        postCodeMap.get(postNummer).tilbud.add(tilbud);
    }

    public void addUser(User user) {
        String postNummer = user.getPostcode();
        if(postCodeMap.get(postNummer) == null){
            postCodeMap.put(postNummer,new TilbudEmail());
        }
        postCodeMap.get(postNummer).users.add(user);
        postCodeMap.get(postNummer).bynavn = user.getBynavn();
    }

    public Set<String> getPostCodes() {
        return postCodeMap.keySet();
    }

    public List<TilbudEmail> buildEmail(String postCode) {
        List<TilbudEmail> result = new ArrayList<>();

        TilbudEmail emailTemplate = postCodeMap.get(postCode);

        List<Modtager> modtagere = Modtager.builder(emailTemplate.users);

        if(emailTemplate.users != null && emailTemplate.users.size() > 0){
            String ugenummer = createUgeNummer().toString();
            for (Modtager batch : modtagere) {
                TilbudEmail email = new TilbudEmail();
                email.receipients = batch;
                email.bynavn = emailTemplate.getBynavn();
                email.setUgenummer(ugenummer);
                email.setTilbud(emailTemplate.tilbud);
                email.setPostCode(postCode);
                result.add(email);
            }
        }
        return result;
    }

    private Integer createUgeNummer() {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        return  calendar.get(Calendar.WEEK_OF_YEAR);
    }




}
