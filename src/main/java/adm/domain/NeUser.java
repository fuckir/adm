package adm.domain;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class NeUser {
    private String firstName;
    private String secondName;
    private String thirdName;
    private String email;
    private String login;
    private String link;
    private String password;

    public NeUser(String firstName, String secondName, String thirdName, String email,
                  String login, String link, String password)
    {
        this.firstName = firstName;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.email = email;
        this.login = login;
        this.link = link;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getLink() {
        return link;
    }

    public String getPassword() {
        return password;
    }
}
