package bitwork.dk;

import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cha on 19-09-2015.
 */
public class ParserTest {
    @Test
    public void parseTest(){
        Notifications users = new Notifications();
        String jsonString = "{'-JyS9TBPGKMW0rIeVsDt':{'bynavn':'Kastrup','email':'carverdk@gmail.com','postcode':'2770'},'-Jz5T_ZtnjdT0AoZkd3T':{'bynavn':'Dragør','email':'carverdk@gmail.com','postcode':'2791'},'-Jz5UGiFMyWaRIqJgnad':{'bynavn':'Dragør','email':'carverdk@yahoo.dk','postcode':'2791'},'-Jz5gwrsB84hUsBO1iL1':{'bynavn':'Dragør','email':'bitwork@gmail.com','postcode':'2791'}}";
        users = new FireBaseParser<Notifications>().parse(jsonString,Notifications.class);
        Assert.assertTrue(users != null);
    }
    @Test
    public void buildTest(){

        Notifications not = new Notifications();
        not.put("key1", new User("sfsd@sdfsfs.dk","bynavn","2222"));
        not.put("key2", new User("sfsd@sdfsfs.dk","bynavn","2222"));


        GsonBuilder builder = new GsonBuilder();
        System.out.println(builder.create().toJson(not).toString());

    }

}
