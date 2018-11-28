package testarray;

import java.util.Arrays;

public class MainClass {
    public static void main(String args[]) throws Exception {
        int array[] = {2,3,7,4,1,5};
        Arrays.sort(array);
        printArray("数组排序结果为", array);
        int index = Arrays.binarySearch(array, 9);
        System.out.println("元素 9 所在位置（负数为不存在）："
                + index);
        int newIndex = -index - 1;
        array = insertElement(array, 9, newIndex);
        printArray("数组添加元素 1", array);

    }

    private static void printArray(String message, int[] array) {
        System.out.println(message + ": [length: " + array.length + "]");
        for (int i : array) {
            System.out.print(i);
            System.out.print(",");
        }
    }
    private static int[] insertElement(int original[],
                                       int element, int index) {
        int length = original.length;
        int destination[] = new int[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }
}