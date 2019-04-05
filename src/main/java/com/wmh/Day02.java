package com.wmh;

/**
 * Created by 周大侠
 * 2019-01-05 0:10
 */
public class Day02 extends Person{
    private int age=2;
    private static String name ="wmh";
     class Inner{
        private String name;

        public Inner(String name) {
            this.name = name;
        }

//        public static void speak(){
//            System.out.println("我在说话");
//        }

    }
    static {
        System.out.println(name);
    }
    {
        System.out.println(age);
        System.out.println(name);
        System.out.println("我是构造代码块");
    }
    public Day02() {
        System.out.println(age);
        System.out.println(name);
        System.out.println("我是无参构造函数");
    }

    @Override
    public void speak(){
        System.out.println("我爱你"+name+" "+age);
    }
    public static void run(){
        System.out.println("我在跑步");
    }
    public static void main(String[] args) throws CloneNotSupportedException {
//        System.out.println(Day02.name);
//        new Day02();
//        Day02 d = new Day02();
//        System.out.println("------------------");
        //        new Day02();
//        System.out.println("------------");
//        System.out.println(Day02.name);
//        Day02.Inner inner = new Day02().new Inner("wmh111");
        Person person = new Person(18,new Car("红色","法拉利"));
        Person person1 = (Person) person.clone();

        System.out.println(person==person1);
        System.out.println(person.getCar() == person1.getCar());
    }


}
class Person implements Cloneable{
    private int age;
    private Car car;
    private static String name="df";

    {
        speak();
        System.out.println("person构造代码块");
    }
    public static void eat(){
        System.out.println("吃东西");
    }
    public Person() {
        System.out.println("person构造函数");
    }

    public Person(int age, Car car) {
        this.age = age;
        this.car = car;
    }

    public void speak(){

        System.out.println("我爱你p"+name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person clone = (Person) super.clone();
        clone.setCar((Car) clone.getCar().clone());
        return clone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Person.name = name;
    }
}
class Car implements Cloneable{
    private String color;
    private String brand;

    public Car(String color, String brand) {
        this.color = color;
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
