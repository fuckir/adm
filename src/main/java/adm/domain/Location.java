package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class Location {
    private Office office;

    @JsonCreator
    public Location(@JsonProperty("office") Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }
}
