import java.util.Arrays;

public class SortingTest2 {
    //插入排序（顺位查找，值与已排序的数组值进行对比，最小值排在前面）
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 6, 2, 2, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
