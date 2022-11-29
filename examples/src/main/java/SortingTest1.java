import java.util.Arrays;

public class SortingTest1 {
    //冒泡排序（循环顺序查找，每次将最大值排在最后面）
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
