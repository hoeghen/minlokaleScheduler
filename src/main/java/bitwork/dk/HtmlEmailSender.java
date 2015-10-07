package bitwork.dk;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.file.StreamDataBodyPart;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URL;

/**
 * Created by cha on 03-10-2015.
 */
public class HtmlEmailSender implements IEmailSender {
    static  String mailgunApiKey = "key-fafe77f0a13240a2b39678542f6124e8";
    private final InputStream htmlStream;
    private final InputStream logoStream;


    public HtmlEmailSender(InputStream htmlStream,InputStream logoStream) {
        this.htmlStream = htmlStream;
        this.logoStream = logoStream;
    }

    @Override
    public ClientResponse sendEmailApi(TilbudEmail email) {
        return sendInlineImage(htmlStream,logoStream,email);
    }


    public ClientResponse sendInlineImage(InputStream htmlStream, InputStream logoStream, TilbudEmail email) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","key-fafe77f0a13240a2b39678542f6124e8"));
        WebResource webResource =client.resource("https://api.mailgun.net/v3/minlokalebutik.dk/messages");

        FormDataMultiPart form = new FormDataMultiPart();
        form.field("from", "admin@minlokalebutik.dk");
        form.field("to", email.receipients);
        form.field("subject", "MinLokaleButik.dk - Ugens Tilbud");

        String html = getStringFromInputStream(htmlStream);
        String templatedHtml = Templater.apply(email, html);


        form.field("html", templatedHtml);

        form.bodyPart(new StreamDataBodyPart("inline", logoStream, "images/logo.png",MediaType.APPLICATION_OCTET_STREAM_TYPE));
        return webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, form);
    }


    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}
