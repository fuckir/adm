package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class CityName {
    private String en;

    @JsonCreator
    public CityName(@JsonProperty("en") String en) {
        this.en = en;
    }

    public String getEn() {
        return en;
    }
}
