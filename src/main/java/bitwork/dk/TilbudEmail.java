package bitwork.dk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cha on 19-09-2015.
 */
public class TilbudEmail {
    String postCode;

    List<Tilbud> tilbud = new ArrayList<Tilbud>();
    List<User> users = new ArrayList<User>();

    String receipients;
    String contents;


    @Override
    public String toString() {
        return "TilbudEmail{" +
                "receipients='" + receipients + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
