package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// 자동으로 getter, setter 등의 메서드를 만들어주는 편리한 라이브러리
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("lombok");
        helloLombok.setAge(20);

        System.out.println("helloLombok = " + helloLombok);
    }
}
