package test.streamAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Animal {
    private final String name;
    private final int age;
    private final Classification classification;

}
