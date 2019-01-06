package com.github.maxwell.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ArrayMainTest {

    /**
     * 二分法查找
     * @param input
     * @param low
     * @param high
     * @param value
     * @return
     */
    private static int binSearch(int[] input, int low, int high, int value){
        if(low > high)
            return  -1;
        int mid = low + ((high - low) >> 1);
//        if(input[mid] == value)
//            return mid;
        if(input[mid] == value){
            //第一个元素相同
            if(mid == 0 || input[mid-1]  != 0 )
                return mid;
            else
                return binSearch(input, low, high-1, value);
        }
        else if(input[mid] < value){
            return binSearch(input, mid + 1, high, value);
        } else {
            return binSearch(input, low, mid-1, value);
        }
    }

    public static void main(String[] args){
        int[] b = {8, 11, 11, 19, 23, 27, 33, 45, 67, 98};
        int value = 27;
        int index = binSearch(b, 0, b.length-1, value);
        System.out.println("index:" + index);
    }
}
