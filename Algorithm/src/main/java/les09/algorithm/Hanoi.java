package les09.algorithm;

public class Hanoi {
    public static void hanoi(int n) {
        if (n > 0) {
            func(n, "左", "右", "中");
        }
    }

    public static void func(int i, String start, String end, String other) {
        if (i == 1){
            System.out.println("Mova 1 from" + start + " to" + end);
        } else {
            func(i - 1, start, other, end);
            System.out.println("Mova" +  i + "from" + start + " to" + end);
            func(i - 1, other, end, start);
        }
    }
}
