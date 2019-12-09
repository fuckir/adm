package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class Ru {
    private String ru;

    @JsonCreator
    public Ru(@JsonProperty("ru") String ru) {
        this.ru = ru;
    }

    public String getRu() {
        return ru;
    }
}
