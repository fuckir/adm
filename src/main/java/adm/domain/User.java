package adm.domain;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class User {

    private String login;
    private String lastName;
    private String firstName;
    private String email;
    private String location;
    private String wishList;

    public User(String login, String lastName, String firstName, String email, String location, String wishList) {
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.location = location;
        this.wishList = wishList;
    }

    public String getLogin() {
        return login;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLocation() {
        return location;
    }

    public String getWishList() {
        return wishList;
    }
}
