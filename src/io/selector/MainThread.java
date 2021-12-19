package io.selector;

/**
 * @author: jzh
 * @date: created in 2021/12/19
 * @description:
 * @version: 1.0
 */
public class MainThread {

    public static void main(String[] args) {
        SelectorThreadGroup boss = new SelectorThreadGroup(1);
        SelectorThreadGroup worker = new SelectorThreadGroup(2);
        boss.setWorker( worker);
        boss.bind(8081);
    }
}
