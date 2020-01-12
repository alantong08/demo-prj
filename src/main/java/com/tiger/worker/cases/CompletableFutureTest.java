package com.tiger.worker.cases;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    private static final ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return "hello";
        }).thenApply(s->{
           // System.out.println("this is child thread:"+Thread.currentThread().getName());
            return s+"word";});
       // System.out.println("this is main thread:"+Thread.currentThread().getName());

        long t = System.currentTimeMillis();
        long adjusted = ( t / 5000 ) * 5000 ;
        String value = df.get().format(adjusted);
        System.out.println(value);
    }
}
