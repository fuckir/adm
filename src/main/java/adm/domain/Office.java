package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class Office {
    private City city;

    @JsonCreator
    public Office(@JsonProperty("city") City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }
}
