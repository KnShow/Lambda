package lambda.itcast.cn.lambda;

import java.time.LocalDate;

/**
 * male 男   female 女
 */
public class Person {
    public enum Sex {
        MALE, FEMALE
    }
    private int age;

    public Person(int age, String name, Sex gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    //打印Person的信息
    public void printPerson() {
        // ...
    }
}
