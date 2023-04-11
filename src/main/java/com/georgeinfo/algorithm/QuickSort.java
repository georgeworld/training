package com.georgeinfo.algorithm;

public class QuickSort {
    public static void QuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotpos = Partition(arr, low, high);
            QuickSort(arr, low, pivotpos - 1);
            QuickSort(arr, pivotpos + 1, high);
        }
    }

    public static int Partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                --high;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                ++low;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }
}
