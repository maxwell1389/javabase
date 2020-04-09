package com.github.maxwell.base.array;

public class ArrayMainTest {

    /**
     * 二分法查找
     *
     * @param input
     * @param low
     * @param high
     * @param value
     * @return
     */
    private static int binSearch(int[] input, int low, int high, int value) {
        if (low > high)
            return -1;
        int mid = low + ((high - low) >> 1);
        if (input[mid] == value) {
            return mid;
//        if(input[mid] == value){
            //第一个元素相同
/*            if(mid == 0 || input[mid-1]  != 0 )
                return mid;
            else
                return binSearch(input, low, high-1, value);*/
        } else if (input[mid] < value) {
            return binSearch(input, mid + 1, high, value);
        } else {
            return binSearch(input, low, mid - 1, value);
        }
    }

    /**
     * 二维数组查找
     *
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增， 因此从左下角开始查找，当要查找数字比左下角数字大时。
     * 右移 要查找数字比左下角数字小时，上移。这样找的速度最快。
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        //基本思路从左下角开始找，这样速度最快
        int row = array.length-1;//行
        int column = 0;//列
        //当行数大于0，当前列数小于总列数时循环条件成立
        while((row >= 0)&& (column< array[0].length)){
            if(array[row][column] > target){
                row--;
            }else if(array[row][column] < target){
                column++;
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] b = {8, 11, 11, 19, 23, 27, 33, 45, 67, 98};
        int value = 67;
        int index = binSearch(b, 0, b.length - 1, value);
        System.out.println("index:" + index);

    }
}
