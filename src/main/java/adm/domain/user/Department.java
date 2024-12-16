package adm.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 15.09.2020
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    private String id;
    private String type;
    private String name;

    @JsonCreator
    public Department(@JsonProperty("id") String id,
                      @JsonProperty("type") String type,
                      @JsonProperty("name") String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
}
