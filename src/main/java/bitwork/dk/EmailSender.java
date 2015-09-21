package bitwork.dk;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;

import javax.ws.rs.core.MediaType;

/**
 * Created by cha on 20-09-2015.
 */
public class EmailSender {
    static  String mailgunApiKey = "key-fafe77f0a13240a2b39678542f6124e8";
    public static void sendEmail(TilbudEmail email) {

        System.out.println("email = [" + email + "]");

    }


    public static ClientResponse sendEmailApi(TilbudEmail email) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",mailgunApiKey));
        WebResource webResource =
                client.resource("https://api.mailgun.net/v3/minlokalebutik.dk/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Excited User <mailgun@minlokalebutik.dk>");
        formData.add("to", "carverdk@gmail.com");
        formData.add("to", "carverdk@yahoo.dk");
        formData.add("subject", "Hello Fra scheduler");
        formData.add("text", "Testing some Mailgun awesomness!");
        return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
    }
}
