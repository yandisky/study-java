import java.util.Arrays;

public class SortingTest4 {
    //快速排序
    public static void sort(int[] arr, int firstKey, int lastKey) {
        if (firstKey > lastKey) {
            return;
        }
        int left = firstKey;
        int right = lastKey;
        //基准位，作为中间平均分的值
        int temp = arr[firstKey];
        //需要不断判断左右位置是否重合，因为基准位为中间位，左边小，右边大
        while (left < right) {
            //先看右边，依次往左递减，找出比基准位小的值key
            while (temp <= arr[right] && left < right) {
                right--;
            }
            //再看左边，依次往右递增，找出比基准位大的值key
            while (temp >= arr[left] && left < right) {
                left++;
            }
            //满足条件，将右边小与左边大互换位置
            if (left < right) {
                int t = arr[right];
                arr[right] = arr[left];
                arr[left] = t;
            }

        }
        //最后将基准位设为中间位，到了这里left等于right
        arr[firstKey] = arr[left];
        arr[left] = temp;
        //递归调用左半数组
        sort(arr, firstKey, right - 1);
        //递归调用右半数组
        sort(arr, right + 1, lastKey);
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 6, 2, 2, 5};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
