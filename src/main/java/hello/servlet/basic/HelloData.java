package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter//Lombok 을 활용해서 getter setter 만드는 방법
public class HelloData {
    private String username;
    private int age;
}
