package bitwork.dk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cha on 09-10-2015.
 */
public class Modtager {
    static int maxModtagere = 50;
    static List<Modtager> batches = new ArrayList<Modtager>();

    List<User> users;
    String recipients;


    public Modtager(List<User> users) {
        this.users = users;
        StringBuffer buffer = new StringBuffer();
        for(User user: users){
            buffer.append(user.getEmail()+";");
        }
        recipients = buffer.toString();
    }


    public static List<Modtager> builder(List<User> users) {
        List<List<User>> split = split(users, maxModtagere);
        for(List<User> batch :split){
            batches.add(new Modtager(batch));
        }
        return batches;
    }

    public static List<Modtager> builder(User ... usersArray) {
        List<List<User>> split = split(Arrays.asList(usersArray), maxModtagere);
        for(List<User> batch :split){
            batches.add(new Modtager(batch));
        }
        return batches;
    }


    public static <T> List<List<T>> split(List<T> list, final int length) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int size = list.size();
        for (int i = 0; i < size; i += length) {
            parts.add(new ArrayList<T>(
                list.subList(i, Math.min(size, i + length)))
            );
        }
        return parts;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getRecipients() {
        return recipients;
    }
}
