package bitwork.dk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by cha on 19-09-2015.
 */
public class FireBaseParser<T> {
    public T parse(String jsonString,Class<T> type) {
        Gson gson = new GsonBuilder().create();
        T notifications = gson.fromJson(jsonString, type);
        return notifications;
    }
}
