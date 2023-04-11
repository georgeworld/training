package interview;

import com.georgeinfo.algorithm.QuickSort;
import com.interview.GNode;
import com.interview.MyBtree;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class InterViewTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println("排序前："+Arrays.toString(arr));

        QuickSort qs = new QuickSort();
        qs.QuickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //@Test
    public void testTree() {
        MyBtree tree = new MyBtree();
        GNode<Integer> root = tree.generate(4);
        tree.printTree(root);
    }

    //    @Test
    public void testQuee() {
        Queue<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.poll();
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
