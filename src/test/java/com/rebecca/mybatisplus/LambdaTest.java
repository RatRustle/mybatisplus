package com.rebecca.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * description：lambda表达式学习
 *
 * @author rebecca
 * @date 2022-09-06 11:25
 **/
@SpringBootTest
public class LambdaTest {

    /**
     * 本质：作为接口的实例
     * lambda表达式的使用
     * -> 箭头操作符
     * ->左边：lambda形参列表(其实接口中的抽象方法的形参列表)
     * ->右边: lambda体(其实就是重写的抽象方法的方法体)
     */


    /**
     * test01
     * 无参，无返回值
     */
    @Test
    public void test01(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World1");
            }
        };
        r1.run();
        System.out.println("***************");
        Runnable r2 = () ->System.out.println("Hello World2");
        r2.run();
    }

    /**
     * test02
     * 需要一个参数，无返回值
     */
    @Test
    public void test02(){
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer1.accept("Hello World");
        System.out.println("***************");
        Consumer<String> consumer2 = (String s) -> {
            System.out.println(s);
        };
        consumer2.accept("Hello Rebecca");
    }

    /**
     * test03
     * 数据类型可以省略，即类型推断
     */
    @Test
    public void test03(){
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer1.accept("Hello World");
        System.out.println("***************");
        Consumer<String> consumer2 = (s) -> {  //类型推断
            System.out.println(s);
        };
        consumer2.accept("Hello Rebecca");
    }

    /**
     * test04
     * 只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test04(){
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer1.accept("Hello World");
        System.out.println("***************");
        Consumer<String> consumer2 = s -> {  //类型推断
            System.out.println(s);
        };
        consumer2.accept("Hello Rebecca");
    }

    /**
     * test05
     * 需要两个或以上的参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test05(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        com1.compare(12,21);
        System.out.println("***************");
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        com2.compare(6,3);
    }

    /**
     * test06
     * 只有一条语句时，return与大括号若有,都可以省略
     */
    @Test
    public void test06(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(12,21));
        System.out.println("***************");
        Comparator<Integer> com2 = (o1,o2) ->  o1.compareTo(o2);
        System.out.println(com2.compare(6,3));
    }
}
