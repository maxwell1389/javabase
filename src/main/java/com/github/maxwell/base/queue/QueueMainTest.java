package com.github.maxwell.base.queue;

import java.util.*;

public class QueueMainTest {

    /**
     * 求N个整数中最大的K个数字
     * @param input
     * @param k
     * @return
     */
    private static List<Integer> dealMaxKNums(int[] input, int k){
        List<Integer> lst = new ArrayList<Integer>();
        if(input.length < 1 || k<1 || input.length < k){
            return lst;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(input.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i=0; i<input.length; i++){
            maxHeap.offer(input[i]);
            if(i >= k){
                maxHeap.poll();
            }
        }
        int[] a = new int[k];
        for(int i=0; i<k; i++){
            a[i] = maxHeap.poll();
        }
        for(int i=k-1; i>=0; i--){
            lst.add(a[i]);
        }

/*        for(int i=0; i<k; i++){
            lst.add(a[i]);
        }*/
        return lst;
     }

    /**
     * 无序数组的中位数
     * @param input
     * @return
     */
     private static int dealMidKNums(int[] input){
        if(input.length < 1){
            return -1;
        }
        int mid = 0;
        //奇数
         if(input.length %2 == 0){
             mid = input.length / 2;
         } else{
             mid = (input.length + 1) / 2;
         }
        PriorityQueue<Integer> midHeap = new PriorityQueue<Integer>(mid, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for(int i=0; i<mid; i++){
            midHeap.offer(input[i]);
        }
        for (int j = mid; j<input.length; j++){
            if(input[j] > midHeap.peek()){
                midHeap.poll();
                midHeap.offer(input[j]);
            }
        }
        return midHeap.peek();
    }

    public static void main(String[] args){
        /** 整数中最大的K个数字 开始 **/
        /**
        int[] b = {98,15,12,8,8,4,43,11,15,9,64,2,76,35,11,3,1};
        int k = 3;
        List<Integer> lst = dealMaxKNums(b, k);
        for(Integer ib:lst){
            System.out.print(ib+" ");
        } **/
        /** 结束**/

        /** 无序数组中中位数 开始 **/
        int[] b = {3, 15, 12, 6, 21,30};
        int mid = dealMidKNums(b);
        System.out.printf("%s %n",mid);
        /** 结束**/
    }
}
