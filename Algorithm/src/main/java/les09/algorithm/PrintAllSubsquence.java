package les09.algorithm;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ether
 */
public class PrintAllSubsquence {
    public static void printAllSubsquenceNew(String str) {
        char[] chs = str.toCharArray();
        processNew(chs, 0);
    }

    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    public static void process(char[] str, int i, List<Character> res) {
        if (i == str.length) {
            printList(res);
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process(str, i + 1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(str, i + 1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...

    }

    public static List<Character> copyList(List<Character> res) {
        // ...

        return res;
    }

    public static void processNew(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }

        // 要当前字符的路
        processNew(str, i + 1);
        char tmp = str[i];
        str[i] = 0;

        // 不要当前字符的路
        processNew(str, i + 1);
        str[i] = tmp;
    }
}
