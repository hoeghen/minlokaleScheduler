package bitwork.dk;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.StreamDataBodyPart;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by cha on 03-10-2015.
 */
public class HtmlEmailSender implements IEmailSender {
    static  String mailgunApiKey = "key-fafe77f0a13240a2b39678542f6124e8";
    static  Logger logger = Logger.getLogger(HtmlEmailSender.class.getName());

    private final String html;
    Client client;
    WebResource webResource;


    public HtmlEmailSender(String htmlPath) {
        this.html = StreamHelper.getStringFromInputStream(this.getClass().getResourceAsStream(htmlPath));
        client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","key-fafe77f0a13240a2b39678542f6124e8"));
        webResource =client.resource("https://api.mailgun.net/v3/minlokalebutik.dk/messages");

    }

    @Override
    public ClientResponse sendEmailApi(TilbudEmail email) {
        return sendInlineImage(html,email);
    }


    public ClientResponse sendInlineImage(String html,TilbudEmail email) {

        FormDataMultiPart form = new FormDataMultiPart();
        form.field("from", "admin@minlokalebutik.dk");
        form.field("bcc", email.receipients.getRecipients());
        form.field("subject", "MinLokaleButik.dk - Ugens Tilbud");
        String templatedHtml = Templater.apply(email, html);
        form.field("html", templatedHtml);

        InputStream pngStream = this.getClass().getResourceAsStream("/images/logo.png");
        form.bodyPart(new StreamDataBodyPart("inline", pngStream, "images/logo.png",MediaType.APPLICATION_OCTET_STREAM_TYPE));
        ClientResponse post = webResource.type(MediaType.MULTIPART_FORM_DATA_TYPE).
                post(ClientResponse.class, form);
        if(post.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)){
            logger.info("succesfully send " + email.receipients.getUsers().size() + " emails to " + email.getBynavn());
        }else{
            logger.info("succesfully failed sending " + email.receipients.getUsers().size() + " emails to " + email.getBynavn());
        }
        return post;
    }



}
