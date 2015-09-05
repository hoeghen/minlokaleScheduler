package bitwork.dk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by cha on 30-08-2015.
 */
public class MailerServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        URL url = new URL("https://jobspot.firebaseio.com/notifications.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Gson gson = new GsonBuilder().create();
        User[] users = gson.fromJson(reader, User[].class);

        for (User user : users){
            resp.getWriter().write(" user email =" + user.getEmail());
            resp.getWriter().write("user postcode =" + user.getPostcode());
        }

        URL url2 = new URL("https://jobspot.firebaseio.com/alletilbud.json");
        reader = new BufferedReader(new InputStreamReader(url.openStream()));

         Tilbud[] tilbud = gson.fromJson(reader, Tilbud[].class);


    }



}
