package saomang;

/**
 * @author: jzh
 * @date: created in 2021/11/7
 * @description:
 * @version: 1.0
 */
public class gailv {


    public static void main(String[] args) {
        double a = 0.75/100;
        double b = 1.0 -a;
        double c = 1.0;
        for(int i = 0;i<200;i++){
            c *= b;
        }
        System.out.println(c);
    }
}
