package com.tiger.worker.cases;

import java.util.List;
import java.util.concurrent.*;

public class MultithreadingSum {
    private int sum = 0;
    /**
     * @Description:   多个线程求和
     * @Author: zhaohaiming
     * @CreateDate:  2019/8/2 22:12
     * @Version:   1.0
     */
    public static  class  ShareData implements Callable<Integer> {
        private  volatile  int startIndex ;
        private volatile  int endIndex ;
        private  volatile  int sum  ;

        public  ShareData(int startIndex,int endIndex){
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        public ShareData(){}

        /**
         * 累加和
         * @return
         * @throws Exception
         */
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程名称:"+Thread.currentThread().getName()+"正在进行计算startIndex=="+ startIndex+"到 endIndex="+endIndex);
            for (int index = startIndex ; index <= endIndex;index++ ){
                sum +=index;
                System.out.println("当前线程名称:"+Thread.currentThread().getName()+"的和为:"+sum);
            }
            //为了空格方便好看
            System.out.println();
            System.out.println();
            System.out.println();
            return sum;
        }

        /**
         * 多线程求和startIndex~endIndex
         * @param number
         * @param threadNumber
         * @return
         */
        public Integer getDataSum(int number ,int threadNumber){
            List<Future<Integer>> taskList = new CopyOnWriteArrayList<>();
            ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);//固定指定线程数量
            try {
                for (int index = 0 ; index < threadNumber ;index++){
                    int startIndex = number * index / threadNumber + 1; // 边界条件 开始下标
                    int endIndex = number * (index + 1) / threadNumber; // 边界条件 结束下标

                    Future future = executorService.submit(new ShareData(startIndex,endIndex));
                    //把每个子线程计算结果放到集合中
                    taskList.add(future);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
            //把各个集合中的值遍历求和
            sum = getFutureSum(taskList);
            return  sum;
        }

        /**
         * 遍历结果集，取数
         * @param taskList
         * @return
         */
        public Integer getFutureSum(List<Future<Integer>> taskList) {
            int sumNumber = 0;
            if (taskList.size() == 0 ) return 0;
            try {
                for (Future<Integer> task: taskList){
                    sumNumber +=  task.get();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sumNumber;
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ShareData shareData = new  ShareData();
        int sum = shareData.getDataSum(20000,8);
        System.out.println("最终运行的结果:"+sum);

    }

}

