package selfTest;

/**
 * @author: jzh
 * @date: created in 2021/5/5
 * @description:
 * @version: 1.0
 */
public class A {

    public int a = 2;


//    public A() {
//        System.out.println("父构造"+a);
//    }


    public  A() {
        new Thread(()-> System.out.println(this.a)).start();
    }

    public static void main(String[] args) {
        new A();

    }
}
