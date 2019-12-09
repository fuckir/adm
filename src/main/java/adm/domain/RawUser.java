package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 06.12.17
 */

public class RawUser {
    private String login;
    private String email;
    private Location location;
    private Name name;

    @JsonCreator
    public RawUser(
        @JsonProperty("login") String login,
        @JsonProperty("work_email") String email,
        @JsonProperty("location") Location location,
        @JsonProperty("name") Name name
    )
    {
        this.login = login;
        this.email = email;
        this.location = location;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Location getLocation() {
        return location;
    }

    public Name getName() {
        return name;
    }
}
