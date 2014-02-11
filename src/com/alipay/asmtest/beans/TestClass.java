package com.alipay.asmtest.beans;

public class TestClass {
    private String name;

    public void sayName() {
        System.out.println(name);

    }

    public void sayHello() {

        System.out.println("I am in sayHello method");
        throw new NullPointerException();

    }

    public static void main(String[] args) throws InterruptedException {
        TestClass tc = new TestClass();
        tc.sayName();
        tc.sayHello();

        System.out.println("aaa");
    }
}
