package lambda.itcast.cn.lambda;

import android.util.Log;

import java.time.LocalDate;

public class Teacher extends Person{
    public static final String TAG = "lambda";
    public Teacher(){}

    public Teacher(int age) {
        this.age = age;
    }

    public enum Sex {
        MALE, FEMALE
    }

    private int age;

    public Teacher(int age, String name, Person.Sex gender) {
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
    Person.Sex gender;
    String emailAddress;

    //打印Person的信息
    public void printPerson() {
        Log.e(TAG, "姓名_____" + name + "   年龄________" + age + "   sex____" + gender.name());
    }
}
