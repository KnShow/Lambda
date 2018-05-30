package lambda.itcast.cn.lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private Button helloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorld = findViewById(R.id.helloWorld);
        textLambda();
        testRxLambda();
    }
    private void initTestData() {
//        现在我们有一个Person的集合List roster。我们要在roster中打印出符合某些条件的Person的信息有如下场景
        new Person(20,"壹", Person.Sex.MALE);
        new Person(21,"二", Person.Sex.MALE);
        new Person(22,"叁", Person.Sex.MALE);
        new Person(23,"四", Person.Sex.MALE);
        new Person(24,"五", Person.Sex.MALE);
        new Person(25,"六", Person.Sex.MALE);
        new Person(26,"柒", Person.Sex.MALE);
        new Person(27,"八", Person.Sex.MALE);
        new Person(28,"九", Person.Sex.MALE);
    }
    private void textLambda() {
        //以一个点击事件为例,来来体现lambda的用法与优势
        //常规操作
//        helloWorld.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("你点了按钮");
//            }
//        });
        //Lambda操作
//        helloWorld.setOnClickListener((View v) -> Toast.makeText(MainActivity.this, "你点了按钮", Toast.LENGTH_SHORT).show());

//     # 自己尝试编写Lambda表达式
        initTestData();
    }



    private void testRxLambda() {
        //简单的RxJava使用语法
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("helloWorld");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        //
    }
}
