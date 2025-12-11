package adm.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewRawUser {

    private final String login;
    private final String firstName;
    private final String lastName;
    private final String workPlace;
    private final String workEmail;

    @JsonCreator
    public NewRawUser(@JsonProperty("login") String login,
                      @JsonProperty("firstName") String firstName,
                      @JsonProperty("middleName") String middleName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("workPlace") String workPlace,
                      @JsonProperty("workPhone") String workPhone,
                      @JsonProperty("workEmail") String workEmail,
                      @JsonProperty("position") String position,
                      @JsonProperty("hideMiddleName") String hideMiddleName,
                      @JsonProperty("telegram") String telegram,
                      @JsonProperty("avatarId") String avatarId,
                      @JsonProperty("department") Department department) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workPlace = workPlace;
        this.workEmail = workEmail;
    }

    public String getLogin() {
        return login;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getWorkPlace() {
        return workPlace;
    }
}
