package com.tiger.worker.cases;

public class ThreadJoinTest {

    public static void main(String[] args){
        System.out.println("MainThread run start.");

        //启动一个子线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA run start.");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("threadA run finished.");
            }
        });
        threadA.start();

        System.out.println("MainThread join before");
        try {
            threadA.join();    //调用join()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread run finished.");
    }

}
