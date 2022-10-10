package com.qa.test.dingtalk;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * 咖啡店有五个服务员，
 * 咖啡店每秒最多生产三个订单，每个订单有一半的机率咖啡是要加冰的。
 * 每个服务员只做一件事情。
 */
public class Cafe {

    //五个服务员
    static ExecutorService waiters = Executors.newFixedThreadPool(5);

    //加冰的方法
    static Function<OrderItem, Void> iceFun = orderItem -> {
        try {
            //加冰
            Thread.sleep(100);
            orderItem.getDrink().setIced(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    };

    //咖啡的制作方法
    static Function<OrderItem, Void> coffeeFun = orderItem -> {
        try {
            //制作咖啡
            Thread.sleep(900);
            Drink drink = new Drink();
            orderItem.setDrink(drink);
            //如果要加冰
            if (orderItem.isIced()) {
                //等待另一个服务员加冰返回
                waiters.submit(new Waiter(orderItem, iceFun)).get();
            }

            //制作完成
            System.out.println("coffee " + orderItem);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    };

    /**
     * 服务员
     */
    private static class Waiter implements Runnable {

        //服务员拿到的订单
        OrderItem orderItem;

        //服务员要做的事情
        Function<OrderItem, Void> function;

        public Waiter(OrderItem orderItem, Function<OrderItem, Void> function) {
            this.orderItem = orderItem;
            this.function = function;
        }

        @Override
        public void run() {
            //服务员处理订单
            function.apply(orderItem);
        }
    }


    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        for (int i = 0; ; i++) {
            //一秒钟最多生产3个单子
            for (int j = 0; j < rand.nextInt(4); j++) {
                //模拟随机加冰订单
                boolean iced = rand.nextInt(5) % 2 == 0;
                //交给服务员进行制作
                waiters.submit(new Waiter(new OrderItem(iced, 3 * i + j), coffeeFun));
            }
            Thread.sleep(1000);
        }
    }


    public static class OrderItem implements Serializable {

        private static final long serialVersionUID = 1L;

        //是否加冰
        private boolean iced = false;

        /**
         * 订单ID
         */
        private int orderId;

        //咖啡
        private Drink drink;

        public Drink getDrink() {
            return drink;
        }

        public void setDrink(Drink drink) {
            this.drink = drink;
        }

        public OrderItem(boolean iced, int orderId) {
            this.iced = iced;
            this.orderId = orderId;
        }

        public boolean isIced() {
            return this.iced;
        }

        public void setIced(boolean iced) {
            this.iced = iced;
        }

        @Override
        public String toString() {
            return orderId + " OrderItem{" +
                    "drink=" + JSON.toJSONString(drink) +
                    '}';
        }
    }

    public static class Drink implements Serializable {

        private static final long serialVersionUID = 1L;

        private boolean iced;


        public Drink() {
        }

        public Drink(boolean iced) {

            this.iced = iced;
        }


        public boolean isIced() {
            return this.iced;
        }

        public void setIced(boolean iced) {
            this.iced = iced;
        }


        @Override
        public String toString() {
            return (iced ? "Iced" : "Hot") + " Coffee";
        }

    }

}