package org.delphy.testredis;

public class Person {
    public synchronized void sleep(String threadName) {
        System.out.println("get sleep lock:" + threadName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("sleep error:" + threadName);
        }
        System.out.println("sleep end:" + threadName);
    }
    public synchronized void eat(String threadName)  {
        System.out.println("get eat lock:" + threadName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("eat error:" + threadName);
        }
        System.out.println("eat end:" + threadName);
    }

    private static void testSingleMethod(){
        final Person p1 = new Person();
        Thread sleepThread = new Thread(() -> p1.sleep("sleepThread"));
        Thread sleepThreadExt = new Thread(() -> p1.sleep("sleepThreadExt"));
        sleepThread.start();
        sleepThreadExt.start();
    }

    private static void testMutipleMethod(){
        final Person p1 = new Person();
        Thread sleepThread = new Thread(() -> p1.sleep("sleepThread"));
        Thread eatThread = new Thread(() -> p1.eat("eatThread"));
        sleepThread.start();
        eatThread.start();
    }

    private static void testMutipleMethodAndMutiple(){
        final Person p1 = new Person();
        final Person p2 = new Person();
        Thread sleepThread = new Thread(() -> p1.sleep("sleepThread"));
        Thread eatThread = new Thread(() -> p2.eat("eatThread"));
        sleepThread.start();
        eatThread.start();
    }

    public static void main(String[] args) {
//        Person.testSingleMethod();
//        Person.testMutipleMethod();
        testMutipleMethodAndMutiple();
    }
}
