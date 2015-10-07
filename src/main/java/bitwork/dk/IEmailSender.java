package bitwork.dk;

import com.sun.jersey.api.client.ClientResponse;

/**
 * Created by cha on 03-10-2015.
 */
public interface IEmailSender {
    ClientResponse sendEmailApi(TilbudEmail email);
}
