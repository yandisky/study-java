import java.util.Arrays;

public class SortingTest3 {
    //选择排序（相反的插入排序，在未排序的数组中找出最小值排在前面）
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 6, 2, 2, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
