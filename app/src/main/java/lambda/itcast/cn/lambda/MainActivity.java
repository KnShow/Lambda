package lambda.itcast.cn.lambda;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_Lambda";
    private Button helloWorld;
    private List<Person> roster = new ArrayList();
    private List<Teacher> rosTeacher = new ArrayList<>();
    private List<Integer> integerList;
    private Map<String, Function<Integer, Person>> map;
    private Person testPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorld = findViewById(R.id.helloWorld);
        initTestData();
//        textLambda();
        testRxLambda();
    }


    private void initTestData() {
//        现在我们有一个Person的集合List roster。我们要在roster中打印出符合某些条件的Person的信息有如下场景
        integerList = Arrays.asList(23, 12, 34, 56, 76, 12);
        testPerson = new Person(24, "壹", Person.Sex.MALE);
        roster.add(new Person(24, "壹", Person.Sex.MALE));
        roster.add(new Person(20, "壹二", Person.Sex.MALE));
        roster.add(new Person(21, "壹二叁", Person.Sex.FEMALE));
        roster.add(new Person(22, "叁四五六", Person.Sex.MALE));
        roster.add(new Person(23, "柒八九拾零", Person.Sex.FEMALE));
        roster.add(new Person(25, "六六", Person.Sex.MALE));
        roster.add(new Person(26, "柒柒柒", Person.Sex.FEMALE));
        roster.add(new Person(27, "八八八八", Person.Sex.MALE));
        roster.add(new Person(28, "九九九九九", Person.Sex.MALE));
        roster.add(new Person(17, "拾拾拾拾拾拾", Person.Sex.MALE));
        roster.add(new Person(18, "零零零零零零零", Person.Sex.FEMALE));

        rosTeacher.add(new Teacher(20, "壹", Person.Sex.MALE));
        rosTeacher.add(new Teacher(21, "二", Person.Sex.FEMALE));
        rosTeacher.add(new Teacher(22, "叁", Person.Sex.MALE));
        rosTeacher.add(new Teacher(23, "四", Person.Sex.FEMALE));
        rosTeacher.add(new Teacher(24, "五", Person.Sex.MALE));
        rosTeacher.add(new Teacher(25, "六", Person.Sex.MALE));
        rosTeacher.add(new Teacher(26, "柒", Person.Sex.FEMALE));
        rosTeacher.add(new Teacher(27, "八", Person.Sex.MALE));
        rosTeacher.add(new Teacher(28, "九", Person.Sex.MALE));
        rosTeacher.add(new Teacher(17, "拾", Person.Sex.MALE));
        rosTeacher.add(new Teacher(18, "零", Person.Sex.FEMALE));
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void textLambda() {
        // 1、打印字符串长度  (String s) -> s.length();
        //   匿名内部类实现
        // printStrLength("1234567", new StringConsumer<String, Integer>() {
        //            @Override
        //            public <R> Integer apply(String s) {
        //                return s.length();
        //            }
        //        });
        // Lambda 表达式实现
//        printStrLength("1234567", (String s) -> s.length())

        //因为 function和java lambda的区别就是生成的字节码不一样
        //printStrLength("1234567", String::length);

        //2、判断大小  (Apple a)-> a.getWeight>150;
//        printCompareWeight(roster, (Person p) -> p.getAge() > 25);

        //3.Function函数的使用  以将一个String集合列表映射到包含每个String长度的Integer集合列表
//        List<Integer> map = map(roster, (Person person) -> person.name.length());
//        map(roster, (Person::getName));
////        map(roster,p->p.name.length());
//        for (Integer integer :
//                map) {
//            Log.e(TAG, integer + "");
//        }

        //#方法引用
//        Function<String,Integer> stringToInteger = (String s) -> Integer.parseInt(s);
//        Function<String,Integer> stringToInteger2 =Integer::parseInt;

//        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
//        BiPredicate<List<String>, String> contains2 = List::contains;

        //      //构造函数引用  Supplier 不接收参数，返回一个T
//        Supplier<Person> c1 = () -> new Person();
//        Person p1 = c1.get();
////        等价于
//        Supplier<Person> c2 = Person::new;
//        Person p2 = c2.get();

        //如果Person的构造函数的签名是Person(Integer age),那么他就适合在function接口的签名
//        Function<Integer, Person> f1 = (Integer i) -> new Person(i);
//        //等价于
//        Function<Integer, Person> f2 = Person::new;


        /**
         * Three由Integer构成的List中的每个元素都通过我们前面定义的类似的map方法传递给了Person的构造函数，
         *得到了一个具有不同重量学生的List：
         */
//        map(integerList,Person::new);

        //不将构造函数实例化却能够引用它，这个功能有一些有趣的应用。例如，你可以使用Map来
        //将构造函数映射到字符串值。你可以创建一个giveMeFruit方法，给它一个String和一个Integer，
        //它就可以创建出不同重量的各种水果：
//        map = new HashMap<>();
//        map.put("teacher", Teacher::new);
//        map.put("student", Student::new);

//        ColorFunction<Integer, Integer, Integer, Color> colorF = Color::new;

        //Lambda方法和引用实战 案例：用不同的排序策略对Person列表进行排序
        //#1传递代码
        roster.sort(new AppComparator());
        //#2 匿名内部类
        roster.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //#3使用Lambda表达式
        Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());
        roster.sort(comparator);
        //等价于
        roster.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        roster.sort(comparing((a) -> a.getName()));
        //#4使用方法引用
        roster.sort(comparing(Person::getName));

        //##复合Lambda表达式的有用方法
        Comparator<Person> comparing = comparing(Person::getAge);
        //*要求按年龄递减排序
        comparing(Person::getName).reversed();
        //*两个年龄一样时进一步按姓名排序
        comparing(Person::getAge).reversed().thenComparing(Person::getName);

        //## 谓词复合
        //先判断 性别是否MALE
        Predicate<Person> pe = (p) -> p.gender == Person.Sex.MALE;
        //negate 否定 非
        pe.negate();
        // and

        //or
    }

    public class AppComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public Color map3(Integer a, Integer b, Integer c, ColorFunction<Integer, Integer, Integer, Color> colorf) {
        return colorf.apply(a, b, c);
    }

    public interface ColorFunction<T, R, K, M> {
        M apply(T t, R r, K k);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person giveMePerson(String person, Integer age) {
        return map.get(person.toLowerCase()).apply(age);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public List<Person> map2(List<Integer> list, Function<Integer, Person> f) {
        List<Person> result = new ArrayList<>();
        for (Integer i :
                list) {
            result.add(f.apply(i));
        }
        return result;
    }


    /**
     * 解释一下这里的泛型的作用
     *
     * @param src      真实数据
     * @param function Lambada 表达式，做具体转换工作的角色(函数式接口)
     * @param <T>      原始数据类型
     * @param <R>      转换后的数据类型
     * @return
     * @Desc String集合列表映射到包含每个String长度的Integer集合列表  这里的String对应的T,Integer对应的就是R
     */
    @TargetApi(Build.VERSION_CODES.N)
    public <T, R> List map(List<T> src, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : src) {
            result.add(function.apply(t));
        }
        return result;
    }


    public void printStrLength(String s, StringConsumer<String, Integer> consumer) {
        if (!TextUtils.isEmpty(s)) {
            Log.i(TAG, consumer.apply(s) + "");
        }
    }

    public interface StringConsumer<T, R> {
        <R> Integer apply(T t);
    }

    public void printCompareWeight(List<Person> src, CompareTor<Person, Boolean> compareTor) {
        for (Person p : src) {
            if (compareTor.compare(p)) {
                p.printPerson();
            }
        }
    }

    public interface CompareTor<T, R> {
        R compare(T t);
    }


    private void testRxLambda() {


    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
