package bitwork.dk;

/**
 * Created by cha on 30-08-2015.
 */
public class User {
    private String email;
    private String postcode;
    private String bynavn;

    public User(String email, String postcode, String bynavn) {
        this.email = email;
        this.postcode = postcode;
        this.bynavn = bynavn;
    }

    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }
}
