package logic;

/**
 * @author George (GeorgeWorld@qq.com)
 */
public class CommonTest {
    private static int param_a;
    private static int param_b;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        int a = 10;
        int b = 20;
        exchange(a, b);
        a = param_a;
        b = param_b;
        System.out.println(a);
        System.out.println(b);
    }

    public static void exchange(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        param_a = a;
        param_b = b;
    }
}
