package bitwork.dk;

import com.floreysoft.jmte.Engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cha on 06-10-2015.
 */
public class Templater {

    static public String apply(TilbudEmail email, String html) {
        Engine engine = new Engine();
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("email",email);
        return engine.transform(html, model);
    }

}
