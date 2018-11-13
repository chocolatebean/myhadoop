package com.learn.java.finder;

public class FibonacciSearch{

    private static final int MAXSIZE = 13;

    public static void fibonacci(int[] f){
        f[0] = 1;
        f[1] = 1;
        System.out.print(f[0] + " ");
        System.out.print(f[1] + " ");


        for (int i = 2; i < MAXSIZE; i++) {
            f[i] = f[i-1] + f[i-2];
            System.out.print(f[i] + " ");
        }

        System.out.println();

    }

    public static int fibonacci_search(int[] a, int key, int n){
        int low = 0, high = n-1;
        int mid = 0;
        int k = 0;
        int[] F =  new  int[MAXSIZE];
        fibonacci(F);

        while( n > F[k]-1){ //确保fibonacci数列中的值可以表示整个待搜索数组中的位置
            ++k;
        }

        //将待搜索数组的规模扩展为F[k]-1；原来不足的部分用a[high]填充
        int[] tempArray = new int[F[k]-1];

        for (int i = n; i < F[k]-1; i++) {
            tempArray[i] = a[high];
        }

        for (int i = 0; i < n; i++) {
            tempArray[i] = a[i];
        }

        a = tempArray;

        //对于规模为F[k]-1的数组
        //黄金分割搜索， 每次将数组分为三部分，
        //第一部分为从low（包含）开始的F[k-1]-1个元素，到mid-1（包含）为止; 
        //第二部分即为单个的a[mid],其中 mid = low+F[k-1]-1;
        //第三部分为 从啊mid+1 （包含）开始的F[k-2]-1个元素，到high为止
        //每次循环均遵循这一规律

        while(low <= high){
            mid = low + F[k-1] -1;
            if (a[mid] > key) {
                high = mid -1;
                k = k - 1;
            }else if (a[mid] < key){
                low = mid + 1;
                k = k - 2;
            }else{
                if (mid <= high) {
                    return mid;
                }else {
                    return -1;
                }
            }

        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = {5,15,19,20,25,31,38,41,45,49,52,55,57};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
        System.out.println(fibonacci_search(a,57,13));
    }

}
