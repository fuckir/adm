package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class City {
    private CityName name;

    @JsonCreator
    public City(@JsonProperty("name") CityName name) {
        this.name = name;
    }

    public CityName getName() {
        return name;
    }
}
