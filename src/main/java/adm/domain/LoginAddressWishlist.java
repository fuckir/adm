package adm.domain;

/**
 * @author dmitrys
 * @since 17.12.2020
 */
public class LoginAddressWishlist {
    private String login;
    private String address;
    private String wishlist;

    public LoginAddressWishlist(String login, String address, String wishlist) {
        this.login = login;
        this.address = address;
        this.wishlist = wishlist;
    }

    public String getLogin() {
        return login;
    }

    public String getAddress() {
        return address;
    }

    public String getWishlist() {
        return wishlist;
    }
}
