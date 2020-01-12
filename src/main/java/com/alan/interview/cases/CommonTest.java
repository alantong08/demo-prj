package com.alan.interview.cases;

import java.util.Arrays;

public class CommonTest {

    public static void main(String[] args) {
        int low = 1;
        int high = 6;
        int middle = (low+high)/2;
        System.out.println(middle);

        int [] arrays = {3, 2, 15, 9, 20, 17, 65, 43, 28, 73};
        Arrays.sort(arrays);
        //System.out.println(binarySearch(arrays, 20));
        compareStr();
    }

    public static void compareStr(){
        String a = "AAA";
        String b = new String("AAA");
        System.out.println("a==b?"+(a==b));
        System.out.println("a.equals(b)?"+a.equals(b));

        Integer c = 123;
        Integer d = 123;
        System.out.println("c==d?"+(c==d));
    }

    public static int  binarySearch(int arrays[], int target){

        int low = 0;
        int high = arrays.length;

        while(low<=high){
            int mid=(low+high)/2;
            if(arrays[mid]==target){
                return arrays[mid];
            }

            if(arrays[mid]>target){
                high = mid-1;
            }
            if(arrays[mid]<target){
                low = mid+1;
            }
        }
        return -1;
    }
}
