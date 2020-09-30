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

    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private String workPlace;
    private String workPhone;
    private String workEmail;
    private String position;
    private String hideMiddleName;
    private String telegram;
    private String avatarId;
    private Department department;

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
                      @JsonProperty("department") Department department)
    {
        this.login = login;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.workPlace = workPlace;
        this.workPhone = workPhone;
        this.workEmail = workEmail;
        this.position = position;
        this.hideMiddleName = hideMiddleName;
        this.telegram = telegram;
        this.avatarId = avatarId;
        this.department = department;
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
