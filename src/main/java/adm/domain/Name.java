package adm.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dmitrys
 * @since 03/12/2019
 */
public class Name {
    private Ru last;
    private Ru first;

    @JsonCreator
    public Name(@JsonProperty("last") Ru last,
                @JsonProperty("first") Ru first)
    {
        this.last = last;
        this.first = first;
    }

    public Ru getLast() {
        return last;
    }

    public Ru getFirst() {
        return first;
    }
}
