package test.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(exclude = {"lastName"})
@EqualsAndHashCode(of = {"id", "name"})
public class Lombok {
    private Integer id;
    @Setter
    private String name;
    private String lastName;

    public Lombok() {
    }

    public Lombok(Integer id) {
        this.id = id;
    }

}
